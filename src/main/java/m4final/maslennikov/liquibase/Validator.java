package m4final.maslennikov.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import m4final.maslennikov.connection.ConnectionData;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Validator {
    private static final String VALIDATE_FILE_PATH = "liquibase/changelog.xml";

    public static void validate() {
        Map<String, Object> config = new HashMap<>();
        try (Connection connection = ConnectionData.getConnection()) {
            Scope.child(config, () ->
            {
                Database database = DatabaseFactory.getInstance()
                        .findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Liquibase liquibase = new liquibase
                        .Liquibase(VALIDATE_FILE_PATH, new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts(), new LabelExpression());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
