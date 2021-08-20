# NFP121-2231n-P1

You only need bluej for the project to work.

Design patterns used:
  Iterator design pattern : seen that I have a lot of lists it would be easier for me to implement the iterator design pattern . that way I only need an iterator to pass through 
  the list.
  Composite design pattern : I made a class for the search result called "SearchInfo" that compares the data of the table with the data of the files.And i needed to have some
  SearchInfo that are contained in the same index(query number in the table).So i implemented Composite and made a SearchInfoGroup that holds all the SearchInfo and that has one
  more information and its the query index.
  
Difficulties:
  I am not a heavy commenter so I'm working on commenting the rest of the code.
  I started by making the results inside string arraylists containing string arraylist or hashmaps and that slowed me down alot.
  
Improvements:
  My Project only works with java code any user that want to use it on another type of code needs to fix it.And its quite easy to implement the adapter design pattern for the 
  new type of projects required.
  Not not all search functions work currently so I'm working on fixing that.
