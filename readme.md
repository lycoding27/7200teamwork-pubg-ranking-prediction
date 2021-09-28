# 7200teamwork-pubg-ranking-prediction-team8
This project is designed as the final project of CSYE7200 in Northeastern University, taught by Prof.Robin Hillyard.

## Team members
*Yimu Liu, Ziyang Wei* 

## PlayerUnknown's Battlegrounds (PUBG)
PlayerUnknown's Battlegrounds (PUBG) is an online multiplayer battle royale game developed and published by PUBG Corporation, a subsidiary of South Korean video game company Bluehole.
In the game, up to one hundred players parachute onto an island and scavenge for weapons and equipment to kill others while avoiding getting killed themselves. The available safe area of the game's map decreases in size over time, directing surviving players into tighter areas to force encounters. The last player or team standing wins the round.
## Abstract
Our goal is to create a model which predicts players' finishing placement based on their final stats, on a scale from 1 (first place) to 0 (last place). We preprocessed the data and train 4 different models(Decision Tree, LinearRegresstion, Randomforest and GTR) with 22 features(Kills, boosts, etc). Our program can automatically select the best model based on r2 and rmse. The users can input the information and then predict the ranking(winPlacePerc) of players. 
## How to work
Clone or download the repository to local.

Open the ScalaProject file with IDEA. Run Main.scala in Final Project/src/main/scala/Main.scala.It will take 3-4 minutes to train all machine learning models when you first run Main.scala. Follow the instruction in console to input required features and system will return predictions.

## Bulid 
**Scala** - The program language to implement the program.

**IntelliJ IDEA** - The IDE to development the system.

**Spark** - The framework to develop the Machine Learning process

## Data source
https://www.kaggle.com/c/pubg-finish-placement-prediction

