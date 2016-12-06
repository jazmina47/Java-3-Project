
#Health Clinic Project
###Java-3-Project: CSC3610 Advanced Java
-----

#### Project Description
Create an application to help a new local health care clinic system in Aurora — the City of Lights.

The application will need to have programs that capture and manipulate data of the organization as described below:

* An Arraylist of a variable number of healthcare professionals; doctors, nurses, and support staff (min. 10).

* An Arraylist of a variable number of patients data (min. 20).

* Classes for the following:
  - An Employee class containing first name, last name, title, speciality and their salary. 
  - A Patient class containing first name, last name, date of visit, complaint, an array of 4 Patient objects, and description of their reason for visiting the clinic. 

* The application should offer the user the option of entering patients’ data and diagnosis, and should calculate the cost.

* A sorted list of of employee information should be written to a database.  

* The user should be able to display all information.


* Your application must include the following:
  - Constructors ArraysLists, Stacks, QuequesIterator and ComparatorSets and Maps
  - Hashset, LinkedHashSetSorting
  - Bubble Sort, Merge Sort, QuickSort, Heap Sort, External SortBinary SearchHashing
  - Exception Handling 
  - MessageBox Data 
  - Validation 
  - Database Connection
  - Value Added. Something not listed above 


#### Database Creation 
  To create the database on your local machine (using **mySQL**):
  
  * Save the healthclinic_db.sql file in the folder you want.  
  * Go in to the command line and `cd` into the folder where you saved the file. 
  * Run the `pwd` command to get the path of where the file is located to use as the source of the database. 
```
$ pwd
```
 * Example:
   - In macOS my output is this when I used `pwd`:
   ```
   /Users/jazmina/CSC_Java3/Workspace_JAVA3_Fall2016/Java-3-Project
   ```
  
 * Copy this file path. (when in the **mysql** shell, paste path, then add healthclinic_db.sql to the end of the path)            
run the following in the **mysql** shell:
``` 
> create database healthclinic_db;
> use healthclinic_db;
> source (your_file_path_here)/healthclinic_db.sql
```

This should create the database with the tables and insert statements described in the sql file.
