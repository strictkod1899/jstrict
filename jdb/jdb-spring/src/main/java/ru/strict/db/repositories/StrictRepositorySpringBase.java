package ru.strict.db.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.strict.db.dto.StrictDtoBase;
import ru.strict.db.entities.StrictEntityBase;

import javax.sql.DataSource;

public abstract class StrictRepositorySpringBase<ID, E extends StrictEntityBase, DTO extends StrictDtoBase>
        extends NamedParameterJdbcTemplate
        implements StrictRepositoryAny<ID, E, DTO> {

    public StrictRepositorySpringBase(DataSource dataSource) {
        super(dataSource);
    }
}
