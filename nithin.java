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
