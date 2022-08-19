
## `lib` project(we will include this module to other projects)

1. Create simple spring boot app (name of package for example `com.shared`)

2. Remove main class

3. in build.gradle add `apply false`

ex: 	id 'org.springframework.boot' version '2.7.2' apply false

4. in build.gradle add

dependencyManagement {
	imports {
		mavenBom org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
	}
}

-step 3,4 is made to not create jar from project when compiled

5. settings.gradle should be
	
rootProject.name = 'lib' 

6. in single test class add row

	@SpringBootApplication
	static class TestConfiguration {
	}

	-this is made to run test without errors


7. copy your hibernate classes into your com.shared package


## Project Main

1. in main class add @ComponentScan("com.example") - this is done to make spring scan project

2. in setting.gradle add row

include 'lib'  

--this is made to include module into project


3. in your root gradle property (~/.gradle/gradle.properties)

add row

sharedPath=/path/to/folder/with/your shared lib


4.  in setting.gradle add rows

def sharedPath = file(properties.get('sharedPath'))

rootProject.children.findAll { it.name.equals('`lib`') }.each { project ->
    project.projectDir = new File(sharedPath, project.name.replace(':', '/'))
}	

--this done to find module and add it to project


