package org.home.timetable;

import org.boris.xlloop.reflect.XLFunction;

import java.util.Arrays;

public class ExcelTest {


    @XLFunction(name = "JavaCall",
            help = "Call corresponding function",
            args = { "function", "data" },
            argHelp = { "Function Name", "Data to provide" },
            category = "JavaCalls")
    public static String[][] javaCall(String function, Object[][] data) {

        System.out.println("Java call : " + function);
        System.out.println("Params    :" + Arrays.deepToString(data));

        return new String[][]{{"1"}};
    }

}
