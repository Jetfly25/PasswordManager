# Password Manager
### Author: Miguel Fuentes

# Introduction
This is an app designed to have a place to store any passwords that you use from any website securely. The goal is to show why a password manager is a helpful tool for protecting users from
hackers. Passwords are the first line of defence against attacks. Having a list of passwords, especially strong ones, is essential to maintaining the security of all systems. However,
memorizing many simultaneously, especially the unique and stronger ones, is exhausting. This project will help inform people who may be skeptical of using a tool like a password manager or need a convenient place to store their passwords safely.

# About this program
The program uses Spring Boot from the Spring framework, which is built with Java, HTML, CSS, and JavaScript. It is based on Spring MVC with OOP concepts. The HTML pages represent the front end, combined with CSS styling and some JavaScript for functionality. This program also utilizes a file-based H2 database built into Spring Boot to store all users and their saved websites.

**Note: All passwords are hashed/encrypted. However, for the purposes of this project, please do NOT use any real passwords when using this program. Please use "fake" passwords, or ones you never use in a real application.**

# How to run this program
Download the .jar file in this repository as a raw file. You can access the file [here](https://github.com/Jetfly25/PasswordManager/blob/main/passwordmanager/passwordmanager.jar). Put the .jar file somewhere accessible. Then open a terminal window and type this into your terminal window: `java -jar passwordmanager.jar`. 

If the .jar is not in the same directory, then do: `java -jar "PATH_TO_JAR_FILE"`, replacing the "PATH_TO_JAR_FILE" with the path to the .jar file you downloaded. 

When you run the file, a new directory called "data" will be created in the same place as the .jar file. In that directory, there is an .mv.db file. This file represents the database that contains all user data saved in the password manager. No data will be stored when you first run it, and it will not transfer to other database files.

You can also clone this repository to your preferred IDE with `git clone https://github.com/Jetfly25/PasswordManager.git`, then switch your directory using `cd passwordmanager`, and run the .jar file there using the same command as before.
