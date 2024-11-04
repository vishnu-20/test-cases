  jdbcTemplate.update(userInsertSql, username, email);


 jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(userInsertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, email);
            return ps;
        }, keyHolder);

        // Retrieve the generated user ID
        Long userId = keyHolder.getKey().longValue();

        // Insert into the `user_profile` table using the generated ID as a foreign key
        String profileInsertSql = "INSERT INTO user_profile (user_id, address, phone) VALUES (?, ?)";
        jdbcTemplate.update(profileInsertSql, userId, address, phone);


  String userInsertSql = "INSERT INTO user (environment_name, username, email, first_name, last_name, password, " +
                "employee_id, group_names_list, dir_group, details, manager, active_status, extension_attributes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(userInsertSql,
                userModel.getEnvironmentName(),
                userModel.getUserName(),
                userModel.getEmail(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getPassword(),
                userModel.getEmployeeId(),
                String.join(",", userModel.getGroupNamesList()),  // Assuming it's a list of strings
                userModel.getDirGroup(),
                userModel.getDetails(),
                userModel.getManager(),
                userModel.getActiveStatus(),
                userModel.getExtensionAttributes() != null ? userModel.getExtensionAttributes().toString() : null // Assuming this is a complex type, you might need to serialize it to a string
        );
