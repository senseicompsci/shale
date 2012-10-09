/* 
This code's specification is to control a SPHERES robot that competes in the Zero Robotics High School tournament retroSPHERES2D.
There are three aspects of the game, each takes place in a different zone. 
1. Dropping dust clouds to slow down the opponent when it enters the zone
2. Collecting a disabled satellite and optional items that refuel and recharge
3. Traversing the opponent's dust clouds to make it to the finish line
There are many implementations and strategies that are possible to play this game. 
The code given drops all dust material in Zone 1, proceeds to Zone 2 and collects the only necessary item, the satellite, 
and proceeds to the finish line in Zone 3 as fast as possible to captialize on the time bonus. 

The objects in the playing field are: 
2 SPHERES satellites, which are controlled by respective teams that compete
2 disabled satellites, which are necessary to collect before proceeding
2 fuel and charge packs, which recharge the satellite, and are optional
up to 10 dust clouds, which are of varying size and slow down enemy SPHERES that enter them 

Further information can be found at http://zerorobotics.mit.edu/ZRHS2012/RetroSPHERES.pdf
API and documentation on non-user defined functions at: http://zerorobotics.mit.edu/ZRHS2012/api/;

Note: this code not able to run, it only defines our robot. This code simply defines a ZRUser, a type of robot that
can be run in a ZRRoboticsGame.There is additional code which creates an instance of the zero robotics game. located
only on the ZR IDE. To run the code, go here after being logged in and select DummyDropper
http://www.zerorobotics.org/web/zero-robotics/my-projects
Or, see pre-run competition submissions at:
http://www.zerorobotics.org/web/zero-robotics/tournament-details?tournamentId=7#c_2DCompetition
and select Team Mass Effect in 9th place in order to see our robot against other nationwide competitiors

Additional Note: Data on our sphere is stored in the ZRState sphere, which is just a size 12 float array. 
It can be retrieved by calling api.getMyZRState(sphere). This call just updates the array with new values. The values
correspond as shown:
sphere[0],[1],[2]: x, y, or z position
sphere[3],[4],[5]: x, y, or z linear velocity
sphere[6],[7],[8]: x, y, or z attitude unit vector
sphere[9],[10],[11]: x, y, or z angular velocity

More details on these and other functions can also be found in the API above. 

AUTHOR: TEAM MASS EFFECT (PALY ZERO ROBOTICS)
*/

#include <string.h>
#include <math.h>
#include "ZRGame.h"
#include "ZR_API.h"
#include "spheres_types.h"
#include "spheres_constants.h"
#include "ctrl_attitude.h"
#include "ctrl_position.h"
#include "find_state_error.h"
#include "math_matrix.h"
#include "ZRUser.hpp"

#undef ZRSIMULATION

extern ZeroRoboticsGame game;
static ZeroRoboticsAPI& api = game.api;

class ZRUser01 : public ZRUser
{
public:

//BEGIN::PAGE::Absolute Value
	// gives the absolute value
	float abs(float value)
	{
		if(value > 0)
		{
			return value;
		}
		else if(value < 0)
		{
			return -1 * value;
		}
		else
		{
			return 0;
		}
	}

//END::PAGE::Absolute Value
//BEGIN::PAGE::gotoItem
	// makes SPHERE go to specified item
	/*
	In order to collect an item, the following conditions must be true:
	1. You must be < 0.05 meters away
	2. You must have an angular velocity of < 2.3 degrees/second
	3. You must have a linear velocity of < 0.01 meters per second
	4. You must turn 90 degrees in any direction once 1,2,3 are true to collect an item.

	The function is able to accept ints 0, 1, and 2, which correspond to items in the game.

	1,2,3 are accounted for in their own various functions, isClose, isStopSpin, isStopMove
	4 is accounted for with the rotation below with the function setAttitudeTarget
	*/
	void gotoItem(int n)
	{
		api.getMyZRState(sphere);
		game.getItemLocation(n, itemLoc);
		api.setPositionTarget(itemLoc);	
		
		if(isClose(itemLoc) && isStopSpin() && isStopMove())
		{
			//the purpose of startTurnX is to make sure you only turn once per item, save fuel
			if(startTurnX == n)
			{
				/*
				changes attitude vector from current position to either 90 degrees clockwise
				90 degrees counterclockwise
				if(startX > 0)
				{
					torques[0] = -sphere[7];
					torques[1] = sphere[6];
				}
				else if(startX < 0)
				{
					torques[0] = sphere[7];
			  		torques[1] = -sphere[6];
				}
	
				api.setAttitudeTarget(torques);
				startTurnX = startTurnX + 1;
			}		
		}
		else
		{
			//stops the robot by applying torque
			if(startTurnX == n && abs(sphere[11]) > minAngularSpeed)
			{			
				rotation[2] = resetRotationTorque; //rotation needed to stop before next item
				api.setTorques(rotation);
			}
		}
	}

//END::PAGE::gotoItem
//BEGIN::PAGE::isClose
	// returns whether an item is close to the SPHERE with pythagorean theorem
	bool isClose(float target[3])
	{
	  api.getMyZRState(sphere);
			
		//this is by pythagorean theorem
	  float distance = sqrt((target[0] - sphere[0])*(target[0] - sphere[0]) 
	      + (target[1] - sphere[1])*(target[1] - sphere[1]));
		
	  return distance < 0.04;
	}

//END::PAGE::isClose
//BEGIN::PAGE::isStop
	// returns whether or not the SPHERE is not spinning this value is stored in sphere[11]
	bool isStopSpin()
	{
		 api.getMyZRState(sphere);
		
		float angularSpeed = abs(sphere[11]);
	  	return angularSpeed < minAngularSpeed;
	}
	
	// returns whether or not the SPHERE is not moving by the pythagorean theorem
	bool isStopMove()
	{
		 api.getMyZRState(sphere);
			
		//this is by pythagorean theorem
		float linearSpeed = sqrt(sphere[3] * sphere[3] + sphere[4] * sphere[4]);
		return linearSpeed < 0.008; //min speed error allowed
	}

//END::PAGE::isStop
//BEGIN::PAGE::main

	/*
	This is where we implement our strategy. It is to drop all of the SPHERE's dust, quickly collect the disabled
	satellite in Zone 2, and proceed to the finish line. 
	*/

	//Declare any variables shared between functions here
	
	float location[3];
	float itemLoc[3];
	float torques[3];
	float velocity[3];
	float rotation[3];
	ZRState sphere;
	int isStart;
	int startTurnX;
	bool startObstacle;
	float startX;
	float startY;
	float minAngularSpeed;
	float resetRotationTorque;
	Obstacle visibleObstacles[10];
	int count;
	
	void init(){
		//This function is called once when your code is first loaded.
	
		//IMPORTANT: make sure to set any variables that need an initial value.
		//Do not assume variables will be set to 0 automatically!
		isStart = 0;
		startObstacle = true;
		startTurnX = 0;
		minAngularSpeed = 0.03;
		
		torques[0] = 0;
		torques[1] = 0;
		
		rotation[0] = 0;
		rotation[1] = 0;
		rotation[2] = 0;
		
		velocity[0] = 0;
		velocity[1] = 0;
		velocity[2] = 0;
		
	}
	
	void loop(){
		
			api.getMyZRState(sphere);
			if(startObstacle)
			{
				game.startObstacle();
				startObstacle = false;
			}		
			if(isStart < 3)
			{
				/*At the start of the game, we can determine which robot we are by
				looking at the starting x value. Red starts at (-0.4, -0.6) while blue starts
				at (0.4, -0.6). This allows us to specify certain values. (usually just to mirror
				the robot), make sure it does the same thing but in a reflected direction.
				*/
				startX = sphere[0];
				if(startX > 0)
				{
					resetRotationTorque = 0.0005;
					torques[2] = -0.005;
				}
				else if(startX < 0)
				{
					resetRotationTorque = -0.0005;
					torques[2] = 0.005;
				}
				api.setTorques(torques);
				isStart = isStart + 1;			
			}
			
			switch(game.getCurrentPhase()) // 1 is area 1, 2 is area 2, 3 is area 3
			{
				case 1:
					//makes item, and proceeds to next zone once below a certain threshold.
					if(game.getRemainingMaterial() < 0.03) 
					{
						game.stopObstacle();
						
						if(abs(sphere[11]) > minAngularSpeed)
						{
							torques[2] = resetRotationTorque;
							api.setTorques(torques);
						}
						velocity[1] = 0.055; //min distance for item 
						api.setVelocityTarget(velocity);
					}
					break;
				
				case 2: //gets item 0, only required one, and gets out of there to nab time bonus
					if(!game.haveObject(0))
					{	
						gotoItem(0);
					}
					else
					{
						location[0] = -itemLoc[0] * 0.5;
						location[1] = 0.12 - (itemLoc[1] - 0.12) * 0.5;
						
						api.setPositionTarget(location);
					}
					break;
				
				case 3: //makes it to the end, shrinks any visible obstacles.
					velocity[1] = -0.07;
					api.setVelocityTarget(velocity);
					count = game.getVisibleObstacles(visibleObstacles);
					if(count != 0)
					{
						for(int i = 1; i <= count; i++)
						{			
							game.shrinkObstacle(i);
						}
					}
					break;
				
				default: break;
			}
	}

//END::PAGE::main

};

static ZRUser01 userInstance;
ZRUser *zruser01 = &userInstance;
