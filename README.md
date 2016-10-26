# Java-3-Project
Health Clinic Project

<health clinic project README description goes here>

To create the database on your local machine:
1.) save this file in the folder you want. 
2.) Go in to the command line and 'cd' into the folder where you saved the file.
3.) run the 'pwd' command to get the path of where the file is located to use as the source of the database. 
$ pwd
For me (in macOS) my output is this when I used 'pwd': /Users/jazmina/CSC_Java3/Workspace_JAVA3_Fall2016/Java-3-Project
4.) copy this file path. (when in the mysql shell, paste path, then add healthclinic_db.sql to the end of the path)
run the following in the mysql shell:
> create database healthclinic_db;
> use healthclinic_db;

> source (your_file_path_here)\healthclinic_db.sql

This should create the database with the tables and insert statements described in this sql file
