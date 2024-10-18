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
