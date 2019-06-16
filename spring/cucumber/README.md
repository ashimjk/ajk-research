# Cucumber

## Gherkin
   Gherkin is a line-oriented language using line endings, indentations and keywords to define documents. Each non-blank line usually starts with a Gherkin keyword, followed by an arbitrary text, which is usually a description of the keyword.
   
   The whole structure must be written into a file with the feature extension to be recognized by Cucumber.
   
   Here is a simple Gherkin document example:
   
```gherkin
Feature: A short description of the desired functionality

    Scenario: A business situation
        Given a precondition
        And another precondition
        When an event happens
        And another event happens too
        Then a testable outcome is achieved
        And something else is also completed
```

## Feature
A Gherkin file is used to describe an application feature that needs to be tested. The file contains the Feature keyword at the very beginning, followed up by the feature name on the same line and an optional description that may span multiple lines underneath.

All the text, except for the Feature keyword, is skipped by the Cucumber parser and included for the purpose of documentation only.

## Scenarios and Steps
A Gherkin structure may consist of one or more scenarios, recognized by the Scenario keyword. A scenario is basically a test allowing users to validate a capability of the application. It should describe an initial context, events that may happen and expected outcomes created by those events.

These things are done using steps, identified by one of the five keywords: Given, When, Then, And, and But.
```
Given: This step is to put the system into a well-defined state before users start interacting with the application. A Given clause can by considered a precondition for the use case.

When: A When step is used to describe an event that happens to the application. This can be an action taken by users, or an event triggered by another system.

Then: This step is to specify an expected outcome of the test. The outcome should be related to business values of the feature under test.

And and But: These keywords can be used to replace the above step keywords when there are multiple steps of the same type.

```
Cucumber does not actually distinguish these keywords, however they are still there to make the feature more readable and consistent with the BDD structure.

## Running Features in Parallel

We can use the cucumber-jvm-parallel-plugin to create a separate runner for each feature/scenario. Then we’ll configure the maven-failsafe-plugin to execute the resulting runners in parallel.

We can customize the cucumber-jvm-parallel-plugin as it has multiple parameters. Here are the ones we used:
```
glue.package: (Mandatory) our integration test package

featuresDirectory: the path to the directory contains our feature files

parallelScheme: can be either SCENARIO or FEATURE where SCENARIO generates one runner per scenario and FEATURE generates one runner per feature
```

Note that:
```
parallel: can be classes, methods or both – in our case classes will make each test class run in a separate thread

threadCount: indicates how many threads should be allocated for this execution
```

Then to run the tests, we can use the command:
```
mvn verify
```