package by.automation.service;

import by.automation.model.Instance;
import static by.automation.service.TestDataReader.*;

public class InstanceCreator {
    public static final String NUMBER_OF_INSTANCES = "testdata.number.of.instances";
    public static final String OPERATING_SYSTEM = "testdata.operating.system";
    public static final String MACHINE_CLASS = "testdata.machine.class";
    public static final String SERIES = "testdata.series";
    public static final String MACHINE_TYPE = "testdata.machine.type";
    public static final String NUMBER_OF_GPU = "testdata.number.of.gpu";
    public static final String GPU_TYPE = "testdata.gpu.type";
    public static final String LOCAL_SSD = "testdata.local.ssd";
    public static final String DATA_CENTER = "testdata.data.center";
    public static final String COMMITTED_USAGE = "testdata.committed.usage";

   public static Instance withDataFromProperties() {
       return new Instance(getData(NUMBER_OF_INSTANCES), getData(OPERATING_SYSTEM), getData(MACHINE_CLASS),
               getData(SERIES), getData(MACHINE_TYPE), getData(NUMBER_OF_GPU), getData(GPU_TYPE), getData(LOCAL_SSD),
               getData(DATA_CENTER), getData(COMMITTED_USAGE));
   }
}
