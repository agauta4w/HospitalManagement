-- Insert seed data into the JPA-managed table `patient_tbl` and use column names
-- that match Spring/Hibernate's default physical naming (snake_case).
INSERT INTO patient_tbl (
    name,
    date_of_birth,
    email,
    gender,
    blood_group
) VALUES
('Rohit S', '1992-04-30', 'rohit.sharma@example.com', 'Male', 'A_Positive'),
('Sneha G', '1998-09-12', 'sneha.gupta@example.com', 'Female', 'A_Negative'),
('Amit K', '1989-11-25', 'amit.kumar@example.com', 'Male','B_Positive'),
('Pooja V', '1995-06-18', 'pooja.verma@example.com', 'Female', 'AB_Positive'),
('Karan M', '2000-01-08', 'karan.mehta@example.com', 'Male', 'O_Negative');