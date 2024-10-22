<script>
  document.getElementById('uploadForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const fileUpload = document.getElementById('fileUpload').files[0]; 
    if (fileUpload) {
      const reader = new FileReader(); 

      reader.onload = function (event) {
        const data = new Uint8Array(event.target.result); 
        const workbook = XLSX.read(data, { type: 'array' }); 

        const firstSheetName = workbook.SheetNames[0];
        const worksheet = workbook.Sheets[firstSheetName];

        const jsonData = XLSX.utils.sheet_to_json(worksheet);

        const userModelList = jsonData.map(row => ({
          firstName: row['First Name'] || null,
          lastName: row['Last Name'] || null,
          password: row['Password'] || null,
          email: row['Email'] || null,
          employeeId: row['Employee Id'] || null,
          environmentName: 'GSNET.lab' 
        }));

        const jsonString = JSON.stringify(userModelList);

        document.getElementById('jsonData').value = jsonString;

        document.getElementById('uploadForm').submit(); 
      };

      reader.readAsArrayBuffer(fileUpload);
    } else {
      alert("Please select a file first.");
    }
  });
</script>


<form id="uploadForm" action="/bulkUserCreationInAD" method="POST">
  <div class="form-group">
    <label for="file">File</label>
    <input type="file" class="form-control-file" name="file" id="fileUpload" accept=".xlsx" aria-describedby="fileHelp">
    <small id="fileHelp" class="form-text text-muted">Upload only .xlsx file</small>
  </div>
  <input type="hidden" name="jsonData" id="jsonData">
  
  <button type="submit" class="btn btn-primary">Submit</button>
</form>


ObjectMapper objectMapper = new ObjectMapper();
    List<ActiveDirectoryUserModel> userModels;
        userModels = objectMapper.readValue(jsonData, new TypeReference<List<ActiveDirectoryUserModel>>() {});


<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>BulkUser Creation in AD</title>
</head>
<body>
    <div layout:fragment="content">
        <h2>Upload .XLSX file to Create Users in AD</h2>
        <form id="uploadForm" th:action="@{/bulkUserCreationInAD}" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">File</label>
                <input type="file" class="form-control-file" name="file" id="fileUpload" accept=".xlsx">
                <small>Upload only .xlsx file</small>
            </div>
            <button type="submit">Submit</button>
        </form>

        <!-- Table for displaying the response -->
        <h3>Result</h3>
        <table id="resultTable" border="1" style="width: 100%;" th:if="${users}">
            <thead>
                <tr>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Employee ID</th>
                    <th>Environment Name</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through the list of users and display in rows -->
                <tr th:each="user : ${users}">
                    <td th:text="${user.userName}"></td>
                    <td th:text="${user.password}"></td>
                    <td th:text="${user.firstName}"></td>
                    <td th:text="${user.lastName}"></td>
                    <td th:text="${user.email}"></td>
                    <td th:text="${user.employeeId}"></td>
                    <td th:text="${user.environmentName}"></td>
                </tr>
            </tbody>
        </table>

       
    </div>
</body>
</html>

