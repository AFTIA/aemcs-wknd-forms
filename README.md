![Build Status](https://github.com/AFTIA/aemcs-wknd-forms/workflows/Build%20and%20Test/badge.svg)
# AEM As A Cloud Service

This is a project template for AEM As A Cloud Service application. It is intended as a best-practice set of examples as well as a potential starting point to develop your own functionality. You can also find a WKND Demo form as a starting point to developing forms in AEMaacs.

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* it.tests: Java based integration tests
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, and templates
* ui.content: contains sample content using the components from the ui.apps
* ui.config: contains runmode specific OSGi configs for the project
* ui.frontend: an optional dedicated front-end build mechanism (Angular, React or general Webpack project)
* ui.tests: Selenium based UI tests
* all: a single content package that embeds all of the compiled modules (bundles and content packages) including any vendor dependencies
* analyse: this module runs analysis on the project which provides additional validation for deploying into AEMaaCS

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

To build all the modules and deploy the `all` package to a local instance of AEM, run in the project root directory the following command:

    mvn clean install -PautoInstallSinglePackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallSinglePackagePublish

Or alternatively

    mvn clean install -PautoInstallSinglePackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

Or to deploy only a single content package, run in the sub-module directory (i.e `ui.apps`)

    mvn clean install -PautoInstallPackage

## Testing

There are three levels of testing contained in the project:

### Unit tests

This show-cases classic unit testing of the code contained in the bundle. To
test, execute:

    mvn clean test

### Integration tests

This allows running integration tests that exercise the capabilities of AEM via
HTTP calls to its API. To run the integration tests, run:

    mvn clean verify -Plocal

Test classes must be saved in the `src/main/java` directory (or any of its
subdirectories), and must be contained in files matching the pattern `*IT.java`.

The configuration provides sensible defaults for a typical local installation of
AEM. If you want to point the integration tests to different AEM author and
publish instances, you can use the following system properties via Maven's `-D`
flag.

| Property | Description | Default value |
| --- | --- | --- |
| `it.author.url` | URL of the author instance | `http://localhost:4502` |
| `it.author.user` | Admin user for the author instance | `admin` |
| `it.author.password` | Password of the admin user for the author instance | `admin` |
| `it.publish.url` | URL of the publish instance | `http://localhost:4503` |
| `it.publish.user` | Admin user for the publish instance | `admin` |
| `it.publish.password` | Password of the admin user for the publish instance | `admin` |

The integration tests in this archetype use the [AEM Testing
Clients](https://github.com/adobe/aem-testing-clients) and showcase some
recommended [best
practices](https://github.com/adobe/aem-testing-clients/wiki/Best-practices) to
be put in use when writing integration tests for AEM.

## Static Analysis

The `analyse` module performs static analysis on the project for deploying into AEMaaCS. It is automatically
run when executing

    mvn clean install

from the project root directory. Additional information about this analysis and how to further configure it
can be found here https://github.com/adobe/aemanalyser-maven-plugin

### UI tests

They will test the UI layer of your AEM application using Selenium technology. 

To run them locally:

    mvn clean verify -Pui-tests-local-execution

This default command requires:
* an AEM author instance available at http://localhost:4502 (with the whole project built and deployed on it, see `How to build` section above)
* Chrome browser installed at default location

Check README file in `ui.tests` module for more details.

## ClientLibs

The frontend module is made available using an [AEM ClientLib](https://helpx.adobe.com/experience-manager/6-5/sites/developing/using/clientlibs.html). When executing the NPM build script, the app is built and the [`aem-clientlib-generator`](https://github.com/wcm-io-frontend/aem-clientlib-generator) package takes the resulting build output and transforms it into such a ClientLib.

A ClientLib will consist of the following files and directories:

- `css/`: CSS files which can be requested in the HTML
- `css.txt` (tells AEM the order and names of files in `css/` so they can be merged)
- `js/`: JavaScript files which can be requested in the HTML
- `js.txt` (tells AEM the order and names of files in `js/` so they can be merged
- `resources/`: Source maps, non-entrypoint code chunks (resulting from code splitting), static assets (e.g. icons), etc.

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html

## AEM SDK Version

The AEM SDK versions are as follows:

- AEM SDK: 2021.5.5309.20210515T190734Z-210429
- AEM Forms SDK: 2021.05.12.04

## Configure Adobe Sign (HTTPS) alternative

In order to quickly configure Adobe AEM to communicate with Adobe Sign install the following tools

- Node (v.12.x.x)
- Ngrok (npm install -g ngrok)
- Run `ngrok http 4502` (this is assuming that AEM is listening over port 4502)
- Now configure AEM Adobe Sign integration using the ngrok provided URL

# Project Setup

## WKND 

This project has a dependency on the WKND project hosted on Github. Please download and install the following version of the AEM WKND project in order to view the form on the correct Site page. https://github.com/adobe/aem-guides-wknd/releases/tag/aem-guides-wknd-0.3.0

Information about the project setup can be found on the release README page which is available here: https://github.com/adobe/aem-guides-wknd/blob/p6.1/README.md

## Adobe Sign Config

The Adobe Sign configuration can be found at the following URL: http(s)://server:port/libs/adobesign/cloudservices/adobesign.html/conf/global 

Make sure to update the client id and client secret with values that are reflective to your envrionment. 
Users can locate the steps required to configure AEM with Adobe Sign here: https://experienceleague.adobe.com/docs/experience-manager-65/forms/adaptive-forms-advanced-authoring/adobe-sign-integration-adaptive-forms.html?lang=en#configure-adobe-sign-scheduler-to-sync-the-signing-status

**Note:** Adobe Sign requires HTTPS in order to be configured, users can refer to `Configure Adobe Sign (HTTPS) alternative` for a quick HTTPS configuration of the AEM server.

## AFC Config

In order to enable the AFC configurations users will need to create an IMS Configuration on their local instance of AEM, users can follow these instructions (https://experienceleague.adobe.com/docs/aem-forms-automated-conversion-service/using/configure-service.html?lang=en#adduseranddevs). 

**Note:** For cloud instances users should be using the Adobe Admin Console to create their Automated Forms Conversion Service configurations.

Once the IMS config has been created users can navigate to http(s)://server:port/mnt/overlay/fd/flamingo/gui/cloudservices/flamingo/properties.html?item=%2Fconf%2Fglobal%2Fsettings%2Fcloudconfigs%2Fflamingo%2Fprod-wknd-automated-forms-conversion-service to view the AFC configuration settings.

## Salesforce FDM Config

Follow the instructions provided in the following document in order to create a Salesforce instance and configure an FDM with Salesforce https://experienceleague.adobe.com/docs/experience-manager-learn/forms/adaptive-forms/using-adaptive-forms-with-sales-force-integration-tutorial.html?lang=en#aem-forms-1

**Note:** A dev salesforce server should be acquired in order to get an integration going. 

### Creating a Salesforce Entry (Lead Example)

- Click the square on the top left
- Click on Setup (top left)
- Click Object Manager (top left)
- Search for Lead in the top right search box
- Click Field and Relationships (left side bar)
- Click New (top right)
- Select field type (select text if the data contains strings and numbers)
- Click Next (top right)
- Provide a field label (i.e. province)
- Provide a field length (Maximum 255)
- Provide a field name (i.e. province)
- Click Next 
- Click Next
- Click Save

**Note:** That the API Name of the newly created field is what the binding path of the FDM needs to be set to

**Note:** The field name is the value that the FDM needs to send over 

### Viewing Salesforce Leads

- Click the square on the top left
- Click Marketing (top left)
- Click Leads

## How To Use Derby DB
Once Derby DB is configured, the user has a choice within the form to submit to Derby DB or Salesforce. That choice is linked to the form workflow which will use the Services created in the FDM (Form Data Model) to submit to either Derby DB or Salesforce.

### How To Query Derby DB in FDM
- Navigate to AEM Start Page
- Select Forms -> Data Integrations -> WKND
- Locate the `WKND Vacations` FDM and select edit
- Navigate to "Services" From the top of the middle Section
- Select the Service to be Queried (In this example it is the "get" Service)
- Click on "Test Service" from the toolbar at the top
- Now enter JSON key-value pairs to Query the Derby DB. In this example, the Primary Key for the Derby DB is `lead_id`
- Once the query is constructed click test
**Note:** A sample Query will to get a user with `lead_id` 2 in JSON Formate is `{"lead_id":2}`

### How To Post To Derby in FDM
- Follow the steps above to Navigate to the `WKND Vacations` FDM
- - Navigate to "Services" From the top of the middle Section
- Select the Service to be Queried (In this example it is the "insert" Service)
- A sample insert query is pre-populated, modify the query and click Test. If this returns True, you have succesfuly inserted the data in Derby. 
**Note:** Below is a sample insert Query to Derby DB. When inserted, the `lead_id` will incerement by 1.
```
{
    "derby_lead": {
        "FirstName": "Lorem laboris Ut culpa",
        "LastName": "fugiat irure in reprehenderit qui",
        "Phone": "ut eu exercitation velit",
        "email": "aliquip esse ad",
        "BirthDate": "qui deserunt veniam Ut",
        "StreetAddress": "mollit ea labore et",
        "city": "irure enim mollit proident",
        "PostalCode": "in minim consequat et",
        "province": "velit esse anim",
        "country": "mollit esse"
    }
}
```

## Install

Once the Adobe WKND project is installed, then this project can be installed using the `mvn clean install -Padobe-public -PautoInstallSinglePackage` command in the root of the project.

Users can then view the form on the WKND page by opening up this URL: http(s)://server:port/content/wknd/us/en/adventures/west-coast-cycling.html?wcmmode=disabled

## Output Container

Follow the steps in the `Set up a local development environment and initial development project.pdf` in order to have a working Output container in your environment.

System URL's
- http://localhost:8008/systemenv

## Create Users

Follow the documentation provided here to create users within AEM https://experienceleague.adobe.com/docs/experience-manager-65/forms/administrator-help/setup-organize-users/adding-configuring-users.html?lang=en#administrator-help

- Create the following users:
    - George Lang
        - id: george.lang
        - groups:
            - workflow-users
            - forms-users

## Viewing Form with Sites

WKND Form Experience Fragment is located http://localhost:4502/content/experience-fragments/wknd/us/en/adventures/wknd-form/master.html?wcmmode=disabled

In order to start the story, navigate to the http://localhost:4502/content/wknd/language-masters/en/adventures/west-coast-cycling.html?wcmmode=disabled page and click the fourth tab to view the WKND Vacations form.

Once the user fills out all the necessary fields and submits they then get redirected to Adobe Sign for a signature. 

George Lang logs in and views the application assiged to him. He then approves the application which then inserts the form data into Salesforce were the lead gen team can review the new lead.

**Note:** 
- The WKND project must be installed first
- The WKND Forms project must be installed
- Adobe Sign must be configured
- Salesforce data source must be configured
- George Lang must be created

## We.Finance and We.Gov

The WE forms are located under the following folder http://localhost:4502/aem/forms.html/content/dam/formsanddocuments/we--forms/forms the `WE.Base Form` supports rendering both forms (We.Gov and We.Finance).

### Rendering Form Type
- Load the WE.Base Form in the form editor
- Edit the Form Container
- Change the Adaptive Form Theme from `We.Gov Form Theme` to `We.Finance Form Theme`

**Note:** If you change the theme on this form make sure to change the theme on the Thank you form http://localhost:4502/editor.html/content/forms/af/we--forms/forms/thank-you.html

**Note:** The logo for the DoR is set to be We.Gov because the logo for the DoR is derived from the template and not the theme, the DoR property makes reference to the fileReference property within the guideheader node of the guideimage within the form template.

### Email Configuration

- Login to the http://localhost:4502/system/console/configMgr
- Edit the Day CQ Mail Service and add the necessary information to connect to your SMTP server
- Edit the WKND FORM SUBMISSION workflow
- Open the Send Rejection Email step
- Edit the Sender Details panel to submit using your email address

# Contributors

- [Patrique Legault](mailto:patrique.legault@aftia.com)