# How to use
## Required:
- Java
- NodeJS
- A Text Editor
- Github access token

## How to use:
- **Step 1**
  Create a repository on which to save the server (private is recommended)
- **Step 2**
  Choose a folder on your machine where to initialize a git repository (git init) and transfer all the server files
- **Step 3**
  Upload manually the server files to the github page (first time only)
  use 
  *git add .* 
  *git commit* 
  *git push*
- **Step 4**
  Fill the **locationinfo.json** file (you can find it in package) with the correct informations
    - "location": The folder where the server is saved
    - "token": Github token
    - "url": URL of the github repository, Attention: Enter the URL without https://
    - "serverDir": The folder containing the program to launch (if it is not a jar **edit line 48 with the right command to run**)
    - "bot" (optional): you can run a bot if you want
- **Step 5**
  Run the GetUpdatedServer.jar every time you want to open the server on your machine

## Command to run:
- cd /path/to/project
- cd package
- java -jar 'GetUpdatedServer.jar'


