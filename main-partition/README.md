# The Main Partition

- It is a separation between application component and main component.
- It is a plugin architecture.

## app partition

- core application functionality.
- this is where most of the application component lives.
- it can be sub-divided into a bunch of different modules.

## main partition

- it contains the main program.
- low level details like factories, configuration data.
- it should be kept small and subdivision should be limited.
- it is the entry points from main into the rest of the application.
- it can use various pattern to communicate with app partition like factories and strategies.
- it is dependent upon application partition only not vice-versa.

## Summary

- the dependency between these partition should point in one direction and one direction only.
- they should cross the line pointing towards the application partition.
in essence, main is a plugin to the application
- This uses the technique called "Dependency Injection"

## Solution to Switch Case

- Switch statement that live in the main partition usually do no harm, so as long as
all the cases stay down here in the main partition and so long as all the dependencies
cross the line pointing towards the application then nothing that happens in the application
can effect the main partition. Therefore main partition remains independently deployable,
and the switch statements herewind up doing no harm.

- Switch statements down in main are safe, because we've partitioned main to be
an independently deployable plugin. All the source code point in the right direction.
And this is true to any independently deployable module.

- Switch case statement are safe, so long as the source code dependencies all point
in the right direction.

## Note

- If your application is composed of independently deployable plugins, 
and it should be, then all of those plugins should know about the central core 
of the application. And the central core shouldn't know anything about 
the plugins at all.

- All the source code dependencies should put inwards 
from the plugins towards the core. No software dependency should point outwards, 
away from the core, and towards the plugins.

- Switch statements in plugins are harmless so long as the dependencies point
in the right direction, and the plugins are isolated.
