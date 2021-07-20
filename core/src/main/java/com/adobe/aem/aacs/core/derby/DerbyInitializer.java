package com.adobe.aem.aacs.core.derby;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

@Component(service = DBInitializer.class, immediate = true)
public class DerbyInitializer implements DBInitializer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Reference(target = "(&(objectclass=javax.sql.DataSource)(datasource.name=wknd-derby))")
    private DataSource dataSource;

    @Override
    @SuppressWarnings({"squid:S2658", "squid:S2095", "squid:S2221"})
    public Boolean initialize() {
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
        } catch (Exception e) {
            logger.error("Failed to get a database connection", e);
            return Boolean.FALSE;
        }

        try (Statement stmt = connection.createStatement()) {
            String createTable = IOUtils.toString(
                    this.getClass().getClassLoader().getResourceAsStream("derby/drop-table.sql"),
                    StandardCharsets.UTF_8.name());
            stmt.execute(createTable);
            logger.info("Lead table deleted");
        } catch (Exception e) {
            logger.warn("No LEAD table located - moving on");
        }

        try (Statement stmt = connection.createStatement()) {
            String createTable = IOUtils.toString(
                    this.getClass().getClassLoader().getResourceAsStream("derby/create-table.sql"),
                    StandardCharsets.UTF_8.name());
            stmt.execute(createTable);
        } catch (SQLException e) {
            // Derby error codes
            // http://db.apache.org/derby/docs/10.8/ref/rrefexcept71493.html
            if (!e.getSQLState().equals("X0Y32")) {
                logger.error("Caught an unexpected error in the table creation process", e);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            logger.error("Failed to create lead table", e);
            return Boolean.FALSE;
        }

        try (Statement stmt = connection.createStatement()) {
            String insertTable = IOUtils.toString(
                    this.getClass().getClassLoader().getResourceAsStream("derby/insert-table.sql"),
                    StandardCharsets.UTF_8.name());
            stmt.execute(insertTable);
        } catch (Exception e) {
            logger.error("Failed to insert into lead table", e);
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
