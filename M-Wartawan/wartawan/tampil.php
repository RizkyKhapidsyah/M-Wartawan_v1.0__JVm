    <?php
    $un = $_POST["username"];
    $cr = $_POST["cr"];

    $conn = mysql_connect("localhost","root","");
    mysql_select_db("wartawan");

    $query = "SELECT * FROM data_pegawai WHERE NIP = '$un'";
    $result = mysql_query($query) or die("Unable to verify user because : " . mysql_error());

$rows = array();
while ($r = mysql_fetch_array($result)) {
    echo $r[$cr];
}
?>