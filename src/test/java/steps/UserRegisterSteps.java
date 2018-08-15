package steps;

import com.odde.massivemailer.model.ContactPerson;
import com.odde.massivemailer.model.Mail;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UserRegisterSteps {

    private MassiveMailerSite site = new MassiveMailerSite();
    private WebDriverWrapper driver = site.getDriver();
    private Mail mail = new Mail();

    @Given("^\"([^\"]*)\" which in \"([^\"]*)\" and \"([^\"]*)\" is a contact already$")
    public void is_a_contact_already(String email, String country, String city) throws Throwable {
        site.addContactPage().addContact(email, country, city);
    }

    @When("^Add A Contact \"([^\"]*)\" at \"([^\"]*)\" and \"([^\"]*)\"$")
    public void addAContact(String email, String country, String city) throws Throwable {
        site.addContactPage().addContact(email, country, city);
    }

    @Then("^Page Should Contain \"([^\"]*)\"$")
    public void pageShouldContain(String text) throws Throwable {
        driver.pageShouldContain(text);
    }

    @And("^Page Should Fail$")
    public void pageShouldFail() throws Throwable {
        assertTrue(driver.getCurrentUrl().contains("status=failed"));
    }

    @When("^Admin add a new contact \"([^\"]*)\" with email: \"([^\"]*)\"$")
    public void admin_add_a_new_contact_with_email(String name, String email) throws Throwable {
        site.addContactPage().addContact(email, "Japan", "Tokyo");
    }

    @Then("^An confirmation email is sent to \"([^\"]*)\" from: \"([^\"]*)\"$")
    public void an_confirmation_email_is_sent_to_from(String email, String fromAddres) throws Throwable {
        String token = "123123123asbs";
        mail.setContent("http://localhost:8060/massive_mailer/initial_password?token=" + token);
    }

    @When("^\"([^\"]*)\" click the link in the email$")
    public void click_the_link_in_the_email(String arg1) throws Throwable {
        driver.visit(mail.getContent());
    }

    @When("^\"([^\"]*)\" set password to \"([^\"]*)\"$")
    public void set_password_to(String name, String password) throws Throwable {
        site.initializePasswordPage().setPassword(password);
        driver.visit("http://localhost:8060/massive_mailer/initialize_password_success.jsp");
    }

    @Then("^Show success page$")
    public void show_success_page() throws Throwable {
        driver.pageShouldContain("Success!!");
    }

    @When("^Admin add a new contact \"([^\"]*)\" with invalid email: \"([^\"]*)\"$")
    public void admin_add_a_new_contact_with_invalid_email(String name, String email) throws Throwable {
        site.addContactPage().addContact(email, "Japan", "Tokyo");
    }

    @Then("^Contact page show \"([^\"]*)\"$")
    public void contact_page_show(String errorMsg) throws Throwable {
        driver.pageShouldContain(errorMsg);
    }

    @Then("^Contact was not created$")
    public void contact_was_not_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Mail was not sent$")
    public void mail_was_not_sent() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Admin add a new contact \"([^\"]*)\" with valid email: \"([^\"]*)\"$")
    public void admin_add_a_new_contact_with_valid_email(String name, String email) throws Throwable {
        site.addContactPage().addContact(email, "Japan", "Tokyo");
    }

    @Then("^Contact list page show \"([^\"]*)\"$")
    public void contact_list_page_show(String email) throws Throwable {
        String contactTable = driver.findElementById("contactTable").getText();
        assertTrue(contactTable.contains(email));
    }

    @Given("^Admin add a new contact Yang with email: \"([^\"]*)\"$")
    public void admin_add_a_new_contact_Yang_with_email(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Yang change the token in the url to \"([^\"]*)\" and access the new url$")
    public void yang_change_the_token_in_the_url_to_and_access_the_new_url(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^\"([^\"]*)\" message is shown$")
    public void message_is_shown(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Yang cannot set password$")
    public void yang_cannot_set_password() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There are the following contacts in the CSV file that do not exist in the system$")
    public void there_are_the_following_info_in_the_CSV_file(DataTable contacts) throws Throwable {
        List<String> contactString = contacts.asList(String.class);

        PrintWriter pw = new PrintWriter(new File(System.getProperty("java.io.tmpdir") + "/contactsUploadTest.csv"));
        StringBuilder contactToWrite = new StringBuilder();

        contactToWrite.append(contactString.get(0));
        contactToWrite.append('\n');

        for (int i = 1; i < contactString.size(); i++) {
            String contactDetail = contactString.get(i);
            contactToWrite.append(contactDetail);
            contactToWrite.append('\n');
        }

        pw.write(contactToWrite.toString());
        pw.close();
    }

    @When("^I upload the CSV file$")
    public void i_upload_the_CSV_file() throws Throwable {
        site.visit("add_contact_batch.jsp");
        driver.clickUpload();
    }

    @Then("^There must be two more contacts added$")
    public void there_must_be_two_more_contacts_added(DataTable emailList) throws Throwable {
        driver.expectAlert("Batch Contacts Uploaded");
        checkContactsAreCreated(emailList.asList(String.class));
        deleteCSVFile();
    }

    private void checkContactsAreCreated(List<String> emails) {
        site.visit("contactlist.jsp");
        for (String email : emails) {
            driver.pageShouldContain(email);
        }
    }

    private void deleteCSVFile() {
        File csvFile = new File(System.getProperty("java.io.tmpdir") + "/contactsUploadTest.csv");
        boolean deleteSuccess = false;
        if (csvFile.exists()) {
            deleteSuccess = csvFile.delete();
        }
        assertTrue(deleteSuccess);
    }

}
