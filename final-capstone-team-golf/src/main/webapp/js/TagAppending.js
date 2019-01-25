/**
 * 
 */

var healthTopics = ["Protein-rich", "Dirty-Keto", "Low carb", 
					"Warrior diet", "Vegan", "Heart-healthy"];

function appendTagsToDiv(str) {
/*
var arrayFilteredHealthTopics = [];
for (i = 0; i < healthTopics.length; i++) {
if (healthTopics[i].includes(str)) {
arrayFilteredHealthTopics.push(str);
}
}
*/
var userTagInput = $("#testInput").val();
$("#tagsDiv").append('#' + userTagInput + ' ');
healthTopics.push(userTagInput);
//return arrayFilteredHealthTopics;
}

var mysql = require('mysql');

var con = mysql.createConnection({
  healthTopics
});

con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
  //Insert a record in the "customers" table:
  var sql = "INSERT INTO customers (name, address) VALUES (health topics')";
  con.query(sql, function (err, result) {
    if (err) throw err;
    console.log("1 record inserted");
  });
});

