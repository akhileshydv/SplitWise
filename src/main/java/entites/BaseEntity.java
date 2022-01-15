package entites;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BaseEntity<T> {
    T id;
    Date createdOn;
    String createdBy;
    Date lastModifiedOn;
    String lastModifiedBy;
}
