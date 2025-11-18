package persistence;

import modules.CourseModule;

public class CourseModuleCsvStorage extends CsvStorage {

    public CourseModuleCsvStorage() {
        super("src/main/resources/module.csv", CourseModule.class, new String[] {"moduleName", "moduleCode"});
    }

}
