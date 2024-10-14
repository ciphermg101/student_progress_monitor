package studentprogressmonitor;

import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author CHRIS
 */
public class MainController implements Initializable {

    @FXML
    private TextArea usernamearea;

    @FXML
    private PasswordField passwordarea;

    @FXML
    private TextField passwordtext;

    @FXML
    private CheckBox showpasswordbutton;
    // SignUpPage FXML buttons and text fields

    @FXML
    private TextField TSCNotext;

    @FXML
    private TextField TeacherFirstNametext;

    @FXML
    private TextField TeacherLastNametext;

    @FXML
    private TextField Emailtext;

    @FXML
    private ComboBox<String> SecurityQuestion1Combo;

    @FXML
    private ComboBox<String> SecurityQuestion1Combo1;

    @FXML
    private TextField secquiz1answertext;

    @FXML
    private TextField secquiz2answertext;

    @FXML
    private PasswordField pass1text;

    @FXML
    private PasswordField pass2text;

    @FXML
    private TextField Regnoarea;

    @FXML
    private TextField StudentFirstNamearea;

    @FXML
    private TextField LastNamearea;

    @FXML
    private TextField Surnamearea;

    @FXML
    private DatePicker Enrolldate;

    @FXML
    private DatePicker DOBarea;

    @FXML
    private TextField FatherFirstName;

    @FXML
    private TextField FatherLastName;

    @FXML
    private TextField MotherFirstName;

    @FXML
    private TextField MotherLastName;

    @FXML
    private TextField GuardianFirstName;

    @FXML
    private TextField GuardianLastName;

    @FXML
    private TextField FatherContact;

    @FXML
    private TextField MotherContact;

    @FXML
    private TextField GuardianContact;

    @FXML
    private TextField FatherOccupation;

    @FXML
    private TextField MotherOccupation;

    @FXML
    private TextField GuardianOccupation;

    @FXML
    private TextField SubjectCodearea;

    @FXML
    private TextField SubjectNamearea;

    @FXML
    private TextField regnoarea;

    @FXML
    private TextField subcode;

    @FXML
    private TextField Form1MidTerm1AverageMarks;

    @FXML
    private TextField Form1EndTerm1AverageMarks;

    @FXML
    private TextField Form1MidTerm2AverageMarks;

    @FXML
    private TextField Form1EndTerm2AverageMarks;

    @FXML
    private TextField Form1MidTerm3AverageMarks;

    @FXML
    private TextField Form1EndTerm3AverageMarks;

    @FXML
    private TextField Form3MidTerm1AverageMarks;

    @FXML
    private TextField Form3EndTerm1AverageMarks;

    @FXML
    private TextField Form3MidTerm2AverageMarks;

    @FXML
    private TextField Form3EndTerm2AverageMarks;

    @FXML
    private TextField Form3MidTerm3AverageMarks;

    @FXML
    private TextField Form3EndTerm3AverageMarks;

    @FXML
    private TextField Form2MidTerm1AverageMarks;

    @FXML
    private TextField Form2EndTerm1AverageMarks;

    @FXML
    private TextField Form2MidTerm2AverageMarks;

    @FXML
    private TextField Form2EndTerm2AverageMarks;

    @FXML
    private TextField Form2MidTerm3AverageMarks;

    @FXML
    private TextField Form2EndTerm3AverageMarks;

    @FXML
    private TextField Form4MidTerm1AverageMarks;

    @FXML
    private TextField Form4EndTerm1AverageMarks;

    @FXML
    private TextField Form4MidTerm2AverageMarks;

    @FXML
    private TextField Form4EndTerm2AverageMarks;

    @FXML
    private TextField Form4MidTerm3AverageMarks;

    @FXML
    private TextField Form4EndTerm3AverageMarks;

    @FXML
    private TextField studentregno;

    @FXML
    private TextField actiontaken;

    @FXML
    private DatePicker DOffence;

    @FXML
    private TextArea casedescription;

    @FXML
    private TextField RegNoSearchArea;

    @FXML
    private TableView<Student> StudentProgressTable;

    @FXML
    private TableColumn<Student, String> SearchReg_No;

    @FXML
    private TableColumn<Student, String> SearchFirst_Name;

    @FXML
    private TableColumn<Student, String> SearchLast_Name;

    @FXML
    private TableColumn<Student, String> Form1_Average;

    @FXML
    private TableColumn<Student, String> Form2_Average;

    @FXML
    private TableColumn<Student, String> Form3_Average;

    @FXML
    private TableColumn<Student, String> Form4_Average;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private ResultSet result1;

    /**
     * Establishes a database connection.
     * 
     * @return The database connection.
     * @throws SQLException If an SQL exception occurs during the connection.
     */
    public Connection connection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/studentprogressmonitor";
            String username = "root";
            String password = "root";
            connect = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error establishing a database connection", e);
        }
        return connect;
    }

    /**
     * Logs in the user.
     * 
     * @param event The ActionEvent triggering the method.
     * @throws SQLException If an SQL exception occurs.
     */
    @FXML
    public void login(ActionEvent event) {
        alertMessages alert = new alertMessages();

        if (usernamearea.getText().isEmpty()) {
            alert.errorMessage("Username CANNOT BE BLANK ");
        } else if (passwordarea.getText().isEmpty()) {
            alert.errorMessage("Password is Required ");
        } else {
            try {
                String checkUsername = "SELECT * FROM credentials WHERE username=?";
                connect = connection();

                if (connect != null && !connect.isClosed()) {
                    try (PreparedStatement preparedStatement = connect.prepareStatement(checkUsername)) {
                        preparedStatement.setString(1, usernamearea.getText());

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                String checkCredential = "SELECT * FROM credentials WHERE username=? AND password=?";
                                try (PreparedStatement credentialStatement = connect
                                        .prepareStatement(checkCredential)) {
                                    credentialStatement.setString(1, usernamearea.getText());
                                    credentialStatement.setString(2, passwordarea.getText());
                                    try (ResultSet credentialResult = credentialStatement.executeQuery()) {
                                        if (credentialResult.next()) {
                                            alert.successMessage("Logged-in Successfully");

                                            Platform.runLater(() -> {
                                                try {
                                                    Parent root = FXMLLoader
                                                            .load(getClass().getResource("Dash_board.fxml"));
                                                    Stage stage = new Stage();
                                                    Scene scene = new Scene(root);
                                                    stage.setWidth(1300);
                                                    stage.setHeight(700);
                                                    stage.setResizable(true);
                                                    stage.setScene(scene);
                                                    stage.setTitle("DashBoard");
                                                    stage.show();
                                                    ((Node) event.getSource()).getScene().getWindow().hide();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                    alert.errorMessage("Error loading Dash_board.fxml");
                                                }
                                            });
                                        } else {
                                            alert.errorMessage("Incorrect Password, Try Again");
                                        }
                                    }
                                }
                            } else {
                                alert.errorMessage(usernamearea.getText() + " Account not Found, Sign Up");
                            }
                        }
                    }
                } else {
                    alert.errorMessage("Database Server Connection is Down");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Signs up a new user.
     * 
     * @throws SQLException If an SQL exception occurs.
     */
    @FXML
    public void signup() throws SQLException {
        alertMessages alert = new alertMessages();

        if (TSCNotext.getText().isEmpty()) {
            alert.errorMessage("TSC Number Cannot be Blank");
        } else if (TeacherFirstNametext.getText().isEmpty()) {
            alert.errorMessage("First Name is Required");
        } else if (TeacherLastNametext.getText().isEmpty()) {
            alert.errorMessage("Last Name is Required");
        } else if (Emailtext.getText().isEmpty()) {
            alert.errorMessage("Email is Required");
        } else if (secquiz1answertext.getText().isEmpty()) {
            alert.errorMessage("Set the Answer to the First Security Question");
        } else if (secquiz2answertext.getText().isEmpty()) {
            alert.errorMessage("Set the Answer to the Second Security Question");
        } else if (pass1text.getText().isEmpty()) {
            alert.errorMessage("Password is Required ");
        } else if (pass1text.getText().length() < 8) {
            alert.errorMessage("Password is too short");
        } else if (!pass1text.getText().equals(pass2text.getText())) {
            alert.errorMessage("Password does not match ");
        } else {
            try {
                String checkUsername = "SELECT * FROM teachers WHERE TSCNo=?";
                connect = connection();

                prepare = connect.prepareStatement(checkUsername);
                prepare.setString(1, TSCNotext.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(TSCNotext.getText() + " Username is already taken");
                } else {
                    String insertData = "INSERT INTO teachers (TSCNo, TeacherFirstName,TeacherLastName, email ) VALUES (?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, TSCNotext.getText());
                    prepare.setString(2, TeacherFirstNametext.getText());
                    prepare.setString(3, TeacherLastNametext.getText());
                    prepare.setString(4, Emailtext.getText());

                    prepare.executeUpdate();

                    String insertDataQuiz = "INSERT INTO credentials (username, password, securityQuestion1, securityQuestion2, securityQuestion1Answer, securityQuestion2Answer) VALUES (?, ?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertDataQuiz);
                    prepare.setString(1, TSCNotext.getText());
                    prepare.setString(2, pass1text.getText());
                    prepare.setString(3, (String) SecurityQuestion1Combo.getSelectionModel().getSelectedItem());
                    prepare.setString(4, (String) SecurityQuestion1Combo1.getSelectionModel().getSelectedItem());
                    prepare.setString(5, secquiz1answertext.getText());
                    prepare.setString(6, secquiz2answertext.getText());

                    prepare.executeUpdate();
                    alert.successMessage("Registered Successfully");
                    alert.successMessage("Your TSCNo will be your username during login");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connect != null) {
                        connect.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void showPassword() {
        if (showpasswordbutton.isSelected()) {
            passwordtext.setText(passwordarea.getText());
            passwordtext.setVisible(true);
            passwordarea.setVisible(false);
        } else {
            passwordarea.setText(passwordtext.getText());
            passwordtext.setVisible(false);
            passwordarea.setVisible(true);
        }
    }

    private String[] securityQuestionsList = { "What is your chilhood nickname?", "What is your favourite color?",
            "What is your the name of your mentor?", "What is your favourite sport?" };

    public void securityQuestions() {
        if (SecurityQuestion1Combo != null) {
            List<String> secList = new ArrayList<>();
            secList.addAll(Arrays.asList(securityQuestionsList));
            ObservableList<String> listData = FXCollections.observableArrayList(secList);
            SecurityQuestion1Combo.setItems(listData);
            SecurityQuestion1Combo1.setItems(listData);
        } else {
            System.err.println("SecurityQuestion1Combo is null. Unable to set items.");
            System.err.println("SecurityQuestion1Combo1 is null. Unable to set items.");
        }
    }

    /**
     * Enrolls a new student.
     * 
     * @throws SQLException If an SQL exception occurs.
     */
    @FXML
    public void enrollStudent() throws SQLException {
        alertMessages alert = new alertMessages();

        if (Regnoarea.getText().isEmpty()) {
            alert.errorMessage("Registration Number Cannot be Blank");
        } else if (StudentFirstNamearea.getText().isEmpty()) {
            alert.errorMessage("First Name is Required");
        } else if (Surnamearea.getText().isEmpty()) {
            alert.errorMessage("Surname is Required");
        } else if (LastNamearea.getText().isEmpty()) {
            alert.errorMessage("Last Name is Required");
        } else if (DOBarea.getValue() == null) {
            alert.errorMessage("Select your Date of Birth");
        } else if (Enrolldate.getValue() == null) {
            alert.errorMessage("Enrollment Date Required");
        } else {
            try {
                String checkRegNo = "SELECT * FROM students WHERE RegNo=?";
                connect = connection();
                prepare = connect.prepareStatement(checkRegNo);
                prepare.setString(1, Regnoarea.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(Regnoarea.getText() + " This Registration Number is already registered");
                } else {
                    String insertData = "INSERT INTO students (RegNo, FirstName, Surname, LastName, D_O_B, EnrollDate, FatherFirstName, FatherLastName, MotherFirstName, MotherLastName, GuardianFirstName, GuardianLastName, FatherContact, MotherContact, GuardianContact, FatherOccupation, MotherOccupation, GuardianOccupation ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, Regnoarea.getText());
                    prepare.setString(2, StudentFirstNamearea.getText());
                    prepare.setString(3, Surnamearea.getText());
                    prepare.setString(4, LastNamearea.getText());

                    if (DOBarea.getValue() != null) {
                        java.sql.Date sqlDate = java.sql.Date.valueOf(DOBarea.getValue());
                        prepare.setDate(5, sqlDate);
                    }

                    if (Enrolldate.getValue() != null) {
                        java.sql.Date sqlDate = java.sql.Date.valueOf(Enrolldate.getValue());
                        prepare.setDate(6, sqlDate);
                    }

                    prepare.setString(7, FatherFirstName.getText());
                    prepare.setString(8, FatherLastName.getText());
                    prepare.setString(9, MotherFirstName.getText());
                    prepare.setString(10, MotherLastName.getText());
                    prepare.setString(11, GuardianFirstName.getText());
                    prepare.setString(12, GuardianLastName.getText());
                    prepare.setString(13, FatherContact.getText());
                    prepare.setString(14, MotherContact.getText());
                    prepare.setString(15, GuardianContact.getText());
                    prepare.setString(16, FatherOccupation.getText());
                    prepare.setString(17, MotherOccupation.getText());
                    prepare.setString(18, GuardianOccupation.getText());

                    prepare.executeUpdate();

                    alert.successMessage("Student Enrolled Successfully");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connect != null) {
                        connect.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Registers a new subject.
     * 
     * @throws SQLException If an SQL exception occurs.
     */
    public void registerSubject() throws SQLException {
        alertMessages alert = new alertMessages();

        if (SubjectCodearea.getText().isEmpty()) {
            alert.errorMessage("Subject Code Cannot be Blank");
        } else if (SubjectNamearea.getText().isEmpty()) {
            alert.errorMessage("Subject Name is Required");
        } else {
            try {
                String checkSubCode = "SELECT * FROM subjects WHERE SubjectCode=?";
                connect = connection();
                prepare = connect.prepareStatement(checkSubCode);
                prepare.setString(1, SubjectCodearea.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert.errorMessage(Regnoarea.getText() + " This Subject Code is already registered");
                } else {
                    String insertData = "INSERT INTO subjects (SubjectCode, SubName) VALUES (?, ?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, SubjectCodearea.getText());
                    prepare.setString(2, SubjectNamearea.getText());

                    prepare.executeUpdate();

                    alert.successMessage("Subject registered Successfully");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connect != null) {
                        connect.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Submits marks for a student.
     */
    public void MarksSubmission() {
        alertMessages alert = new alertMessages();

        if (regnoarea.getText().isEmpty()) {
            alert.errorMessage("Registration Number is Required");
        } else if (subcode.getText().isEmpty()) {
            alert.errorMessage("Subject Code Cannot be Blank");
        } else {
            try {
                String checkRegNo = "SELECT * FROM students WHERE RegNo=?";
                connect = connection();
                prepare = connect.prepareStatement(checkRegNo);
                prepare.setString(1, regnoarea.getText());
                result1 = prepare.executeQuery();

                if (!result1.next()) {
                    alert.errorMessage(regnoarea.getText() + " This student is not registered for this subject");
                } else {
                    String checkSubject = "SELECT * FROM subjects WHERE SubjectCode=?";
                    prepare = connect.prepareStatement(checkSubject);
                    prepare.setString(1, subcode.getText());
                    result = prepare.executeQuery();
                    if (!result.next()) {
                        alert.errorMessage("Enter a Valid Subject Code");

                    } else if (result.next() && result1.next()) {
                        alert.errorMessage("The Marks for this subject have already been entered");
                    } else {
                        String insertData = "INSERT INTO markstrend (students_RegNo, SubjectCode, Form1MidTerm1AverageMarks, Form1EndTerm1AverageMarks, Form1MidTerm2AverageMarks, Form1EndTerm2AverageMarks, Form1MidTerm3AverageMarks, Form1EndTerm3AverageMarks, Form2MidTerm1AverageMarks, Form2EndTerm1AverageMarks, Form2MidTerm2AverageMarks, Form2EndTerm2AverageMarks, Form2MidTerm3AverageMarks, Form2EndTerm3AverageMarks, Form3MidTerm1AverageMarks, Form3EndTerm1AverageMarks, Form3MidTerm2AverageMarks, Form3EndTerm2AverageMarks, Form3MidTerm3AverageMarks, Form3EndTerm3AverageMarks, Form4MidTerm1AverageMarks, Form4EndTerm1AverageMarks, Form4MidTerm2AverageMarks, Form4EndTerm2AverageMarks, Form4MidTerm3AverageMarks, Form4EndTerm3AverageMarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, regnoarea.getText());
                        prepare.setString(2, subcode.getText());
                        prepare.setString(3, Form1MidTerm1AverageMarks.getText());
                        prepare.setString(4, Form1EndTerm1AverageMarks.getText());
                        prepare.setString(5, Form1MidTerm2AverageMarks.getText());
                        prepare.setString(6, Form1EndTerm2AverageMarks.getText());
                        prepare.setString(7, Form1MidTerm3AverageMarks.getText());
                        prepare.setString(8, Form1EndTerm3AverageMarks.getText());
                        prepare.setString(9, Form2MidTerm1AverageMarks.getText());
                        prepare.setString(10, Form2EndTerm1AverageMarks.getText());
                        prepare.setString(11, Form2MidTerm2AverageMarks.getText());
                        prepare.setString(12, Form2EndTerm2AverageMarks.getText());
                        prepare.setString(13, Form2MidTerm3AverageMarks.getText());
                        prepare.setString(14, Form2EndTerm3AverageMarks.getText());
                        prepare.setString(15, Form3MidTerm1AverageMarks.getText());
                        prepare.setString(16, Form3EndTerm1AverageMarks.getText());
                        prepare.setString(17, Form3MidTerm2AverageMarks.getText());
                        prepare.setString(18, Form3EndTerm2AverageMarks.getText());
                        prepare.setString(19, Form3MidTerm3AverageMarks.getText());
                        prepare.setString(20, Form3EndTerm3AverageMarks.getText());
                        prepare.setString(21, Form4MidTerm1AverageMarks.getText());
                        prepare.setString(22, Form4EndTerm1AverageMarks.getText());
                        prepare.setString(23, Form4MidTerm2AverageMarks.getText());
                        prepare.setString(24, Form4EndTerm2AverageMarks.getText());
                        prepare.setString(25, Form4MidTerm3AverageMarks.getText());
                        prepare.setString(26, Form4EndTerm3AverageMarks.getText());

                        System.out.println("SQL Query: " + prepare.toString());

                        int rowsAffected = prepare.executeUpdate();

                        if (rowsAffected > 0) {
                            alert.successMessage("Marks Submitted Successfully");
                        } else {
                            alert.errorMessage("Failed to submit marks");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("SQL Error: " + e.getMessage());
            } finally {
                closeConnection();
            }
        }
    }

    /**
     * Records an indiscipline report.
     * 
     * @param event The ActionEvent triggering the method.
     * @throws SQLException If an SQL exception occurs.
     */

    public void indisciplineReports(ActionEvent event) {
        alertMessages alert = new alertMessages();
        try {
            if (studentregno.getText() == null || studentregno.getText().isEmpty()) {
                alert.errorMessage("Registration Number CANNOT BE BLANK");
            } else if (casedescription.getText() == null || casedescription.getText().isEmpty()) {
                alert.errorMessage("Case Details are Required");
            } else if (DOffence.getValue() == null) {
                alert.errorMessage("Date when Indiscipline Case was Committed is Required");
            } else {
                String checkRegNo = "SELECT * FROM students WHERE RegNo=?";
                connect = connection();
                prepare = connect.prepareStatement(checkRegNo);
                prepare.setString(1, studentregno.getText());
                result = prepare.executeQuery();

                if (!result.next()) {
                    alert.errorMessage(studentregno.getText() + " This student is not registered in the School");
                } else {
                    String disreport = "INSERT INTO disciplinereport (students_RegNo, CaseDescription, ActionTaken, DateOfOffence) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connect.prepareStatement(disreport)) {
                        preparedStatement.setString(1, studentregno.getText());
                        preparedStatement.setString(2, casedescription.getText());
                        preparedStatement.setString(3, actiontaken.getText());
                        if (DOffence.getValue() != null) {
                            java.sql.Date sqlDate = java.sql.Date.valueOf(DOffence.getValue());
                            preparedStatement.setDate(4, sqlDate);
                        }
                        preparedStatement.executeUpdate();
                        alert.successMessage("Case Recorded Successfully");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @FXML
    public void searchStudentRecord(ActionEvent event) {
        try {
            alertMessages alert = new alertMessages();

            if (RegNoSearchArea.getText() != null && !RegNoSearchArea.getText().isEmpty()) {
                try {
                    String searchRecord = "SELECT * FROM studentprogressmonitor.students WHERE RegNo LIKE CONCAT('%', ?, '%')";

                    if (result != null) {
                        result.close();
                    }

                    connect = connection();
                    if (connect != null) {
                        try (PreparedStatement prepare = connect.prepareStatement(searchRecord)) {
                            prepare.setString(1, "%" + RegNoSearchArea.getText() + "%");
                            result = prepare.executeQuery();

                            if (!result.next()) {
                                alert.errorMessage(RegNoSearchArea.getText()
                                        + " Record not Found, Enter a valid registration number");
                            } else {
                                alert.successMessage("Record Found");
                            }
                        }
                    } else {
                        alert.errorMessage("Database connection is null");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Registration Number CANNOT BE BLANK ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @FXML
    public void viewTable(ActionEvent event) throws SQLException {
        try {
            alertMessages alert = new alertMessages();

            if (RegNoSearchArea.getText() != null && !RegNoSearchArea.getText().isEmpty()) {
                try {
                    String searchRecord = "SELECT RegNo, FirstName, LastName, Form1Average, Form2Average, Form3Average, Form4Average\n"
                            +
                            "FROM studentprogressmonitor.students, studentprogressmonitor.markstrend\n" +
                            "WHERE studentprogressmonitor.students.RegNo=studentprogressmonitor.markstrend.students_RegNo\n"
                            +
                            "AND studentprogressmonitor.students.RegNo LIKE CONCAT('%', ?, '%')";

                    connect = connection();

                    if (connect != null) {
                        PreparedStatement prepare = connect.prepareStatement(searchRecord);
                        prepare.setString(1, "%" + RegNoSearchArea.getText() + "%");

                        if (result != null) {
                            result.close();
                        }

                        result = prepare.executeQuery();

                        if (!result.next()) {
                            alert.errorMessage(
                                    RegNoSearchArea.getText() + " Record not Found, Enter a valid registration number");

                        } else {
                            alert.successMessage("Record Found");
                            try {
                                StudentProgressTable.getItems().clear();

                                // Create Student object with data from the database
                                Student student = new Student(
                                        result.getString("RegNo"),
                                        result.getString("FirstName"),
                                        result.getString("LastName"),
                                        result.getString("Form1Average"),
                                        result.getString("Form2Average"),
                                        result.getString("Form3Average"),
                                        result.getString("Form4Average"));

                                // Add the student to the table
                                StudentProgressTable.getItems().add(student);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        alert.errorMessage("Database connection is null");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            } else {
                alert.errorMessage("Registration Number CANNOT BE BLANK ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the database connection.
     */

    private void closeConnection() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * @throws Exception
     */
    public void signupButton(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Sign-up");
            stage.setWidth(730);
            stage.setHeight(560);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * @throws Exception
     */
    public void backtologinpage(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login");
            stage.setWidth(570);
            stage.setHeight(490);
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disciplinaryBlackBook(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DisciplinaryReports.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setWidth(593);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("IndisciplineReporting");
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backtodashboard(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Dash_board.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setWidth(1300);
            stage.setHeight(700);
            stage.setResizable(true);
            stage.setScene(scene);
            stage.setTitle("DashBoard");
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller.
     * 
     * @param url The location used to resolve relative paths for the root object.
     * @param rb  The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            securityQuestions();

            if (SearchReg_No != null && SearchFirst_Name != null && SearchLast_Name != null) {
                SearchReg_No.setCellValueFactory(new PropertyValueFactory<>("regNo"));
                SearchFirst_Name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                SearchLast_Name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                Form1_Average.setCellValueFactory(new PropertyValueFactory<>("Form1Average"));
                Form2_Average.setCellValueFactory(new PropertyValueFactory<>("Form2Average"));
                Form3_Average.setCellValueFactory(new PropertyValueFactory<>("Form3Average"));
                Form4_Average.setCellValueFactory(new PropertyValueFactory<>("Form4Average"));
            } else {
                System.err.println(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Student {
        private final String regNo;
        private final String firstName;
        private final String lastName;
        private final String Form1Average;
        private final String Form2Average;
        private final String Form3Average;
        private final String Form4Average;

        // Constructor
        public Student(String regNo, String firstName, String lastName, String Form1Average, String Form2Average,
                String Form3Average, String Form4Average) {
            this.regNo = regNo;
            this.firstName = firstName;
            this.lastName = lastName;
            this.Form1Average = Form1Average;
            this.Form2Average = Form2Average;
            this.Form3Average = Form3Average;
            this.Form4Average = Form4Average;
        }

        // Getters
        public String getRegNo() {
            return regNo;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getForm1Average() {
            return Form1Average;
        }

        public String getForm2Average() {
            return Form2Average;
        }

        public String getForm3Average() {
            return Form3Average;
        }

        public String getForm4Average() {
            return Form4Average;
        }
    }
}
