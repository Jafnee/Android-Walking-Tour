<?php /*I'm so, so sorry if you want to try to read this. It's a horrific mess.*/ ?>
<!doctype html>
<html>
<head>
	<title>Walking Tour Displayer</title>
	<link rel = "stylesheet" type = "text/css" href = "/View/styles.css" />
	
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>

	<!--http://lokeshdhakar.com/projects/lightbox2/-->
	<script src = "/View/lightbox/js/lightbox-2.6.min.js"></script>
	<link rel = "stylesheet" type = "text/css" href = "/View/lightbox/css/lightbox.css" />	
		
	
</head>
<body>
	<div id = "wrapper">
		<div id = "main">			
		<div id = "heading">
			<h1>Walking Tour Displayer</h1>
		</div>
		<?php foreach($routes as $r) { ?>
                    <h2><?php echo $r->get_title(); ?></h2>
                    <p><?php echo $r->get_short_desc(); ?>  
                    <p><b>Duration:<b></p>
                    <p><a href ="/index.php?m=view&id=<?php echo $r->id; ?>">View: click here to display the tour</a></p>
                <?php } ?>	
			
		<div class = "clear"></div>
		</div>
	</div>
</body>
</html>
