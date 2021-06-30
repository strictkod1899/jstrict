package ru.strict.utils;

import lombok.experimental.UtilityClass;
import ru.strict.validate.CommonValidate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

@UtilityClass
public class JdbcUtil {

    public static void executeResourceSqlFile(String sqlFilePath, DataSource dataSource) {
        File sqlFile = ResourcesUtil.getResource(sqlFilePath);
        executeSqlFile(sqlFile, dataSource);
    }

    public static void executeSqlFile(File sqlFile, DataSource dataSource) {
        try {
            String fileContent = Files.readString(sqlFile.toPath());
            Stream.of(fileContent.split(";"))
                    .map(String::trim)
                    .filter(not(CommonValidate::isEmptyOrNull))
                    .forEach(sql -> executeSql(sql, dataSource));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void executeSql(String sql, DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
