package com.napier.coursework;

/*
 * SET08803 Coursework Application - current version #130
 *
 */

import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    // Connect to database
    static MySQLConnection mySQLConnection = new MySQLConnection();
    static Connection sqlConnect;

    public static void main(String[] args) {

        sqlConnect = MySQLConnection.connect();

        SpringApplication.run(App.class, args);

        System.out.println("Group6's website is now up and running. Waiting for http request...");
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String getReport(@RequestParam(value = "id", required = true, defaultValue = "1") int ID,
                            @RequestParam(value = "grouping", required = false, defaultValue = "") String grouping,
                            @RequestParam(value = "limit", required = false, defaultValue = "") String limit) throws ClassNotFoundException, SQLException {

        // Create variable for the html report output
        String htmlOutput = "";

        System.out.println("ID is = "+ ID);
        System.out.println("grouping is = "+ grouping);
        System.out.println("limit is = "+ limit);

        try {

            // We create our handy report generator
            ReportEngine theReport = new ReportEngine();

            // In this mode, we expect variables to be passed - we can also create a loop here to cycle from Reports 1 to 32
            htmlOutput = theReport.generateReport(ID, grouping, limit, sqlConnect);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce report");

        }

        // Produce HTML output in console - should be removed when testing complete
        System.out.println("\n\n\n============ NEW REQUEST ============\n");
        System.out.println("--- HTML START ---");
        //System.out.println(htmlOutput);
        System.out.println("--- HTML END ---");

        return htmlOutput;

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String produceQueryHome()  throws ClassNotFoundException, SQLException {

        // Create variable for the html output
        String htmlOutput;

        htmlOutput = """
                <html>
                add home page and form to GET variables here
                <a href="/report.html?id=5&grouping=North America&limit=10">Test Test Test</a>
                </html>
                """;

        return htmlOutput;

    }

    public static void test() {
        System.out.println("Test in app class executed.");
    }
}