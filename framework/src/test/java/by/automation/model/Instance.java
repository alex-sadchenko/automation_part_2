package by.automation.model;

import java.util.Objects;

public class Instance {
    private String numberOfInstances;
    private String operatingSystem;
    private String machineClass;
    private String series;
    private String machineType;
    private String numberOfGpu;
    private String gpuType;
    private String localSsd;
    private String dataCenter;
    private String committedUsage;

    public Instance(String numberOfInstances, String operatingSystem, String machineClass, String series, String machineType,
                    String numberOfGpu, String gpuType, String localSsd, String dataCenter, String committedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.machineClass = machineClass;
        this.series = series;
        this.machineType = machineType;
        this.numberOfGpu = numberOfGpu;
        this.gpuType = gpuType;
        this.localSsd = localSsd;
        this.dataCenter = dataCenter;
        this.committedUsage = committedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getMachineClass() {
        return machineClass;
    }

    public void setMachineClass(String machineClass) {
        this.machineClass = machineClass;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getNumberOfGpu() {
        return numberOfGpu;
    }

    public void setNumberOfGpu(String numberOfGpu) {
        this.numberOfGpu = numberOfGpu;
    }

    public String getGpuType() {
        return gpuType;
    }

    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }

    public String getLocalSsd() {
        return localSsd;
    }

    public void setLocalSsd(String localSsd) {
        this.localSsd = localSsd;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instance instance = (Instance) o;
        return Objects.equals(numberOfInstances, instance.numberOfInstances) &&
                Objects.equals(operatingSystem, instance.operatingSystem) &&
                Objects.equals(machineClass, instance.machineClass) &&
                Objects.equals(series, instance.series) &&
                Objects.equals(machineType, instance.machineType) &&
                Objects.equals(numberOfGpu, instance.numberOfGpu) &&
                Objects.equals(gpuType, instance.gpuType) &&
                Objects.equals(localSsd, instance.localSsd) &&
                Objects.equals(dataCenter, instance.dataCenter) &&
                Objects.equals(committedUsage, instance.committedUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, operatingSystem, machineClass, series, machineType, numberOfGpu, gpuType, localSsd, dataCenter, committedUsage);
    }

    @Override
    public String toString() {
        return "Instance{" +
                "numberOfInstances='" + numberOfInstances + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", machineClass='" + machineClass + '\'' +
                ", series='" + series + '\'' +
                ", machineType='" + machineType + '\'' +
                ", numberOfGpu='" + numberOfGpu + '\'' +
                ", gpuType='" + gpuType + '\'' +
                ", localSsd='" + localSsd + '\'' +
                ", dataCenter='" + dataCenter + '\'' +
                ", committedUsage='" + committedUsage + '\'' +
                '}';
    }
}
