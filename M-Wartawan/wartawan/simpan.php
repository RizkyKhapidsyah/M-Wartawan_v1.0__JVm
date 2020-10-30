 <?php

    $judul=$_POST['judul'];
    $nip=$_POST['nip'];
    $berita=$_POST['berita'];
    $spinner1=$_POST['spinner1'];

$con=mysqli_connect("localhost","root","","wartawan");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

if (mysqli_query($con,"INSERT INTO berita (NIP, judul, isi, kategori) values ('$nip', '$judul', '$berita' ,'$spinner1')"))
echo 1;
else 
echo 0;

mysqli_close($con);
?> 
