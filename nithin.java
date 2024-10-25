 console.log("Started processing");
    document.getElementById('uploadForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const fileUpload = document.getElementById('fileUpload').files[0];
        if (fileUpload) {
            console.log("---------filename---------", fileUpload.name);
            const reader = new FileReader();
    
            reader.onload = function (event) {
                const data = new Uint8Array(event.target.result);
                const workbook = XLSX.read(data, { type: 'array' });
    
                const firstSheetName = workbook.SheetNames[0];
                const worksheet = workbook.Sheets[firstSheetName];
                const jsonData = XLSX.utils.sheet_to_json(worksheet);
                const dataSize = jsonData.length;
                console.log(dataSize);
    
                let missingFieldsSummary = ""; 
    
                if (dataSize <= 50) {
                    const userModelList = [];
    
                    for (const [index, row] of jsonData.entries()) {
                        const missingFields = []; 
    
                        const userName = row['User Name'];
                        const firstName = row['First Name'];
                        const lastName = row['Last Name'];
                        const password = row['Password'];
                        const email = row['Email'];
                        const employeeId = row['Employee Id'];
                        const manager = row['Manager'];
                        const groupNamesList = row['Group Names List'];
    
                        // Check for missing fields and accumulate them
                        if (!firstName) missingFields.push("First Name");
                        if (!lastName) missingFields.push("Last Name");
                        if (!password) missingFields.push("Password");
                        if (!email) missingFields.push("Email");
                        if (!employeeId) missingFields.push("Employee ID");
                        if (!groupNamesList) missingFields.push("Group Names List");
    
                        // If there are missing fields, log the index and field names
                        if (missingFields.length > 0) {
                            missingFieldsSummary += `Row ${index + 1}: Missing fields - ${missingFields.join(", ")}\n`;
                        } else {
                            // Only push valid entries to the userModelList
                            userModelList.push({
                                userName,
                                firstName,
                                lastName,
                                password,
                                email,
                                employeeId,
                                manager,
                                groupNamesList,
                                environmentName: 'GSMNET_lab'
                            });
                        }
                    }
    
                    if (missingFieldsSummary) {
                        console.error("Missing fields:\n" + missingFieldsSummary);
                        alert("Missing fields:\n" + missingFieldsSummary);
                    } else {
                        // If no missing fields, proceed with form submission
                        const jsonString = JSON.stringify(userModelList);
                        document.getElementById('jsonData').value = jsonString;
                        document.getElementById('uploadForm').submit();
                    }
                } else {
                    const errorMsg = "<h2 color='red'>Please provide an input of only 50 users at a time</h2>";
                    document.getElementById('container').innerHTML = errorMsg;
                    alert("Please provide an input of only 50 users at a time");
                }
            };
    
            reader.readAsArrayBuffer(fileUpload);
            console.log("Upload done");
        } else {
            alert("Please select a file first.");
        }
    });
