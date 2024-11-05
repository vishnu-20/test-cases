public class EntraidExtnAttrInfo {

    private String employeeId;
    private String extnSamaccountname;
    private String extnEmployeetype;
    private String extnFunctionalgroupId;
    private String extnFunctionalgroup;
    private String extnUsertype;
    private String extnCostcenter;
    private String extnWorklocationId;
    private String extnSupervisoryorgId;
    private String extnOrganizationunitId;
    private String extnSupervisoryorg;
    private String extnOrganizationunit;
    private String extnManagementLevel;
    private String extnStorenumber;
    private String extnCareerband;
    private String extnUniqueid;
    private String extnIssupervisor;
    private String extnIsfrontline;

    // Constructor
    public EntraidExtnAttrInfo() {}

    // Parameterized Constructor
    public EntraidExtnAttrInfo(String employeeId, String extnSamaccountname, String extnEmployeetype,
                               String extnFunctionalgroupId, String extnFunctionalgroup, String extnUsertype,
                               String extnCostcenter, String extnWorklocationId, String extnSupervisoryorgId,
                               String extnOrganizationunitId, String extnSupervisoryorg, String extnOrganizationunit,
                               String extnManagementLevel, String extnStorenumber, String extnCareerband,
                               String extnUniqueid, String extnIssupervisor, String extnIsfrontline) {
        this.employeeId = employeeId;
        this.extnSamaccountname = extnSamaccountname;
        this.extnEmployeetype = extnEmployeetype;
        this.extnFunctionalgroupId = extnFunctionalgroupId;
        this.extnFunctionalgroup = extnFunctionalgroup;
        this.extnUsertype = extnUsertype;
        this.extnCostcenter = extnCostcenter;
        this.extnWorklocationId = extnWorklocationId;
        this.extnSupervisoryorgId = extnSupervisoryorgId;
        this.extnOrganizationunitId = extnOrganizationunitId;
        this.extnSupervisoryorg = extnSupervisoryorg;
        this.extnOrganizationunit = extnOrganizationunit;
        this.extnManagementLevel = extnManagementLevel;
        this.extnStorenumber = extnStorenumber;
        this.extnCareerband = extnCareerband;
        this.extnUniqueid = extnUniqueid;
        this.extnIssupervisor = extnIssupervisor;
        this.extnIsfrontline = extnIsfrontline;
    }

    // Getters and Setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getExtnSamaccountname() {
        return extnSamaccountname;
    }

    public void setExtnSamaccountname(String extnSamaccountname) {
        this.extnSamaccountname = extnSamaccountname;
    }

    public String getExtnEmployeetype() {
        return extnEmployeetype;
    }

    public void setExtnEmployeetype(String extnEmployeetype) {
        this.extnEmployeetype = extnEmployeetype;
    }

    public String getExtnFunctionalgroupId() {
        return extnFunctionalgroupId;
    }

    public void setExtnFunctionalgroupId(String extnFunctionalgroupId) {
        this.extnFunctionalgroupId = extnFunctionalgroupId;
    }

    public String getExtnFunctionalgroup() {
        return extnFunctionalgroup;
    }

    public void setExtnFunctionalgroup(String extnFunctionalgroup) {
        this.extnFunctionalgroup = extnFunctionalgroup;
    }

    public String getExtnUsertype() {
        return extnUsertype;
    }

    public void setExtnUsertype(String extnUsertype) {
        this.extnUsertype = extnUsertype;
    }

    public String getExtnCostcenter() {
        return extnCostcenter;
    }

    public void setExtnCostcenter(String extnCostcenter) {
        this.extnCostcenter = extnCostcenter;
    }

    public String getExtnWorklocationId() {
        return extnWorklocationId;
    }

    public void setExtnWorklocationId(String extnWorklocationId) {
        this.extnWorklocationId = extnWorklocationId;
    }

    public String getExtnSupervisoryorgId() {
        return extnSupervisoryorgId;
    }

    public void setExtnSupervisoryorgId(String extnSupervisoryorgId) {
        this.extnSupervisoryorgId = extnSupervisoryorgId;
    }

    public String getExtnOrganizationunitId() {
        return extnOrganizationunitId;
    }

    public void setExtnOrganizationunitId(String extnOrganizationunitId) {
        this.extnOrganizationunitId = extnOrganizationunitId;
    }

    public String getExtnSupervisoryorg() {
        return extnSupervisoryorg;
    }

    public void setExtnSupervisoryorg(String extnSupervisoryorg) {
        this.extnSupervisoryorg = extnSupervisoryorg;
    }

    public String getExtnOrganizationunit() {
        return extnOrganizationunit;
    }

    public void setExtnOrganizationunit(String extnOrganizationunit) {
        this.extnOrganizationunit = extnOrganizationunit;
    }

    public String getExtnManagementLevel() {
        return extnManagementLevel;
    }

    public void setExtnManagementLevel(String extnManagementLevel) {
        this.extnManagementLevel = extnManagementLevel;
    }

    public String getExtnStorenumber() {
        return extnStorenumber;
    }

    public void setExtnStorenumber(String extnStorenumber) {
        this.extnStorenumber = extnStorenumber;
    }

    public String getExtnCareerband() {
        return extnCareerband;
    }

    public void setExtnCareerband(String extnCareerband) {
        this.extnCareerband = extnCareerband;
    }

    public String getExtnUniqueid() {
        return extnUniqueid;
    }

    public void setExtnUniqueid(String extnUniqueid) {
        this.extnUniqueid = extnUniqueid;
    }

    public String getExtnIssupervisor() {
        return extnIssupervisor;
    }

    public void setExtnIssupervisor(String extnIssupervisor) {
        this.extnIssupervisor = extnIssupervisor;
    }

    public String getExtnIsfrontline() {
        return extnIsfrontline;
    }

    public void setExtnIsfrontline(String extnIsfrontline) {
        this.extnIsfrontline = extnIsfrontline;
    }
}


String insertQuery = "INSERT INTO ENTRAID_EXTN_ATTR_INFO (" +
                     "employee_id, extn_samaccountname, extn_employeetype, extn_functionalgroupid, " +
                     "extn_functionalgroup, extn_usertype, extn_costcenter, extn_worklocationid, " +
                     "extn_supervisoryorgid, extn_organizationunitid, extn_supervisoryorg, " +
                     "extn_organizationunit, extn_managementlevel, extn_storenumber, extn_careerband, " +
                     "extn_uniqueid, extn_issupervisor, extn_isfrontline) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


String insertQuery = "INSERT INTO AD_USER_INFO (" +
                     "username, password, first_name, last_name, email, employee_id, manager_ntid, security_groups) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
