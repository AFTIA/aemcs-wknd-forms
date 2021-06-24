package com.adobe.aem.aacs.core.derby;

import org.apache.commons.io.IOUtils;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component(service = DBInitializer.class, immediate = true)
public class DerbyInitializer implements DBInitializer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String DB_URL = "jdbc:derby:memory:wknd-db;create=true";

    @Override
    public Boolean initialize() {
        Connection connection = null;

        try {
            Class.forName(EmbeddedDriver.class.getName()).newInstance();
            // Get a connection
            connection = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            logger.error("Failed to get a database connection", e);
            return Boolean.FALSE;
        }

        try (Statement stmt = connection.createStatement()) {
            String createTable = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("derby/create-table.sql"), StandardCharsets.UTF_8.name());
            stmt.execute(createTable);
        } catch (Exception e) {
            logger.error("Failed to create lead table", e);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }

    @Activate
    protected void activate() {
        logger.info("About to activate system logger");
        if (Boolean.TRUE.equals(this.initialize())) {
            logger.info("Successfully initialized system database");
        } else {
            logger.error("Failed to initialize system database - please review log file for more information");
        }
    }

}
