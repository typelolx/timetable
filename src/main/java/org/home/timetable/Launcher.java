package org.home.timetable;

import org.boris.xlloop.FunctionServer;
import org.boris.xlloop.handler.CompositeFunctionHandler;
import org.boris.xlloop.handler.FunctionInformationHandler;
import org.boris.xlloop.reflect.ReflectFunctionHandler;

import java.io.IOException;

public class Launcher {


    public static void main(String[] args) throws IOException {


        // Create function server on the default port
        FunctionServer fs = new FunctionServer();

//        // Create a reflection function handler and add the Math methods
        ReflectFunctionHandler rfh = new ReflectFunctionHandler();
        rfh.addMethods("Timetable.", ExcelLoader.class);

        // Create a function information handler to register our functions
        FunctionInformationHandler firh = new FunctionInformationHandler();
        firh.add(rfh.getFunctions());

        // Set the handlers
        CompositeFunctionHandler cfh = new CompositeFunctionHandler();
        cfh.add(rfh);
        cfh.add(firh);
        fs.setFunctionHandler(cfh);

        // Run the engine
        System.out.println("Listening on port " + fs.getPort() + "...");
        fs.run();



    }



}
