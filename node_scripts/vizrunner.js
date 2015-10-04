// Dependencies
var mongodb = require("mongodb"),
    http = require('http'),
    url = require('url');

// Set up Mongo
var Db = mongodb.Db,
    Server = mongodb.Server;

// Connect to the MongoDB 'enron' database and its 'emails' collection
var db = new Db("test", new Server("127.0.0.1", 27017, {}));
db.open(function(err, n_db) { db = n_db });
var cursor = db.collection('tweets').find( );
var html = '';

// Setup a simple API server returning JSON
http.createServer(function (req, res) {

  var inUrl = url.parse(req.url, true);

  console.log('cursor');
  res.writeHead(200, {'Content-Type': 'application/json'});
   cursor.each(function(err, doc) {
        if(doc != null) {

	console.log('cursor'+JSON.stringify(doc));
	html = JSON.stringify(doc)+'\n';
        res.write(html, null, 4);
		}
      });



}).listen(1337, '0.0.0.0');

var responseOut = function(html, res) {
   
    
};


console.log('Server running at http://0.0.0.0:1337/');
