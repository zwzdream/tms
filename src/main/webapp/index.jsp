<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap -->
<link href="./resources/js/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="./resources/js/jQuery-3.1.0/jquery-3.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./resources/js/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<button type="button" class="btn btn-default" aria-label="Left Align">
		<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
	</button>

	<button type="button" class="btn btn-default btn-lg">
		<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Star
	</button
		<div style="position: relative;">
			<div class="dropdown">
			  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			    Dropdown
			    <span class="caret"></span>
			  </button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <li><a href="#">Action</a></li>
			    <li><a href="#">Another action</a></li>
			    <li><a href="#">Something else here</a></li>
			    <li role="separator" class="divider"></li>
			    <li><a href="#">Separated link</a></li>
			  </ul>
			</div>
		
	
	</div>
	<p class="lead">
		You can use the mark tag to
		<mark>highlight</mark>
		text.
	</p>
	<blockquote>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Integer posuere erat a ante.</p>
	</blockquote>
	<pre>&lt;p&gt;Sample text here...&lt;/p&gt;</pre>
	<samp>This text is meant to be treated as sample output from a
		computer program.</samp>
	<form>
		<div class="form-group">
			<label for="exampleInputEmail1">Email address</label> <input
				type="email" class="form-control" id="exampleInputEmail1"
				title="Email">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" class="form-control" id="exampleInputPassword1"
				placeholder="Password">
		</div>
		<div class="form-group">
			<label for="exampleInputFile">File input</label> <input type="file"
				id="exampleInputFile">
			<p class="help-block">Example block-level help text here.</p>
		</div>
		<div class="checkbox">
			<label> <input type="checkbox"> Check me out
			</label>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</body>
</html>
