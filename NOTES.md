
Notes and remarks
===================
Tests
----------
I didn't make any unit test in this exercise. Normally for me tests are included in the definition of DONE. I had only 2H for the exercise without any access to 3rd party libraries as Mockito and Assertions. I would like to let you know that in real life I pay a lot attention to quality aspects, for me it's obligatory.

Realisation Aspects
---------
 - I was unable to run the exercise under SDK v22, it worked for me only under v23.
 - Images loader is not completed due to the exercise duration. It can be improved by memory/file system caching. Currently it will get an image from the internet every time when it will be requested.
 - Home made reinvention of the wheel is not my favorite approach. In real life I prefer to use existing open source libraries instead of make my own HttpClient or StreamUtils.
 - I'm adept of SOLID, but I'm sad about the fact that I was not able to fully use SOLID(especially Dependencies Inversion) principle in the exercise due to the prohibition of 3rd party libraries.
 - I left multiple TODOs to indicate my thoughts and notes.
 - I've removed all comments, because in my opinion the code must be self documenting.
 - In real life I prefer to use IoC or Annotations Processors.
 - I'm not a big fan of JSONArray or Map<String,Object>, I prefer to use POJOs/DTOs with automapping if needed.