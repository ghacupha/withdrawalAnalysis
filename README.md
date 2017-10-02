# withdrawalAnalysis

is a poorly implemented rough sketch prototype of a utility that reads data from any form of an excel file xls, or xlsx and saves the same into a database. This is usually after creating objects per row. The cells per row consist the individual fields of the object. This allows me to use JPA to persist them to mysql.The reason I say it is poorly implemented is because the number of fields the object can have is fixed. I would rather create an object with dynamic number of fields then persist it in the database. This way I do not have to adjust my fields for every project. But this project has a short timeine before it needs to be completed. There are also something like 9 worksheets with 65,000 rows each. Immediately you try anything with excel using this, excel will just stop working... So java to the rescue UPDATE: Not good enough. There are 65000 rows of data on each of 9 worksheets. I spent a day trying to emulate a weak form of Yegor Bugayenko's SQL-speaking objects, running on JDBC. I am not happy with the patterns that resulted, because it feels rather unfamiliar. Bad programming is probably embedded in me. However that is not be reason why I am thinking of abandoning this project. It's just with that much data in memory, even the cache(needs work) I implemented following a certain blog, could not stop my VM from running out of memory. And then I began to form an idea. I don't really like it but it seems to be the only way. I think I need to really let the database do it's job. These buggers were created to handle high volumes of data. Why should I suffer to do it myself again? So i thought I will redo the whole thing in a new DSL approach to queries in a different project altogether. Why not? I am thinking I could jooq after all, and run all the queries I need to perform the analysis in the database itself, split columns into different tables, join on my own IDs, convert currencies, and group a summary by month. I even feel like it will take less time.
