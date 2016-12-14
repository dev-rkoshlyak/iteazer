# Run server
mvn compile exec:java@server
# Run integration test
mvn -Dtest=com.iteazer.serverollie.server.GoDownIT#pitchAndRollDown test
