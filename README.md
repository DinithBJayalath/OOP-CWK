# OOP-CWK
This is a real-time ticket simulation system that simulates multiple ticket vendors and buyers buying and selling tickets concurrently.  
This application uses object oriented programming to improve efficiency and reduce unwanted redundancy
while using concurrency and asynchronous programming to simulate real-time activities and improve speed and efficiency.  

The application has 3 main components placed in 2 main folders.  
1. **OOP-CWK-Backend** - Is the folder that contains all the code for the backend of the application, including the REST API endpoints, the core business logic and the proprietary data types needed for the application along with the standalone CLI component of the application.  
This is arranged in to 2 packages in the project folder as,  
&nbsp;&nbsp;&nbsp;&nbsp;  **oopcwkbackend**  
&nbsp;&nbsp;&nbsp;&nbsp;  **oopcwkcli**

2. **OOP-CWK-Frontend** - Is the folder that contains all the code and logic for the frontend of the application. 
#
## Technology stack
The CLI component of the application contained in **oopcwkcli** package uses vanilla Java. while the **oopcwkbackend** package containing the backend of the application is coded using the Spring Boot framework.  
The Front end of the application contained in the **OOP-CWK-Frontend** uses the React js library with TypeScript.  

#
## Usage instructions
* ### CLI
The CLI component of the application is a standalone component that can be run in using a command-line application, a text editor or an IDE.  
To stat the functionality, run the **Main** class of the component and enter the requested data. The simulation will start to run and real-time information will be displayed in the terminal, or the command-line.  
* ### Backend
The backend of the application is configured to run on the URL **"http://localhost:8090"**.  
To start the server, run the **OopCwkBackendApplication** class of the package and the server will start on the specified url.  
* ### Frontend
The frontend of the application is configured to run on the URL **"http://localhost:5173"**.  
To start the frontend of the application, in any command-line tool or text-editor/IDE terminal, navigate in to the **OOP-CWK-Frontend** folder and run the command **"npm run dev"**. Then navigate to the above URL in any web browser.