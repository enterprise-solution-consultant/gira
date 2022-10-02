package cybersoft.javabackend.java18.gira.role.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntity {
    @UtilityClass
    public static class RoleMappedOperation {
        public static final String OPERATION_MAPPED_ROLE = "operations";
        public static final String JOIN_TABLE = "G_ROLE_OPERATION";
        public static final String JOIN_TABLE_ROLE_ID = "G_ROLE_ID";
        public static final String JOIN_TABLE_SERVICE_ID = "G_OPERATION_ID";
    }

    @UtilityClass
    public static class Role {
        public static final String TABLE_NAME = "G_ROLE";
        public static final String NAME = "G_NAME";
        public static final String DESCRIPTION = "G_DESCRIPTION";
        public static final String CODE = "G_CODE";
    }

    @UtilityClass
    public static class UserGroup {
        public static final String TABLE_NAME = "G_GROUP";
        public static final String NAME = "G_NAME";
        public static final String DESCRIPTION = "G_DESCRIPTION";
        public static final String CODE = "G_CODE";
    }

    @UtilityClass
    public static class Operation {
        public static final String TABLE_NAME = "G_OPERATION";
        public static final String NAME = "G_NAME";
        public static final String DESCRIPTION = "G_DESCRIPTION";
        public static final String CODE = "G_CODE";
        public static final String TYPE = "G_TYPE";
    }

    @UtilityClass
    public static class Module {
        public static final String TABLE_NAME = "G_MODULE";
        public static final String NAME = "G_NAME";
        public static final String DESCRIPTION = "G_DESCRIPTION";
        public static final String CODE = "G_CODE";
    }
}
