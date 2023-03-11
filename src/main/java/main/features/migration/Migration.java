package main.features.migration;

import org.flywaydb.core.Flyway;

public class Migration {
    public static void migrate(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./test", "sa", "")
                .load();

        flyway.migrate();
    }
}
