<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Superhero Sightings - ${param.title}</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superhero-sightings.css" rel="stylesheet">
    </head>
    <body class="${param.title == 'Home' ? 'home' : ''}">
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Superhero Sightings</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="${param.title == 'Home' ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}">Home</a>
                    </li>
                    <li class="${param.title == 'Heroes/Villains' ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/displayHeroesPage">Heroes/Villains</a>
                    </li>
                    <li class="${param.title == 'Superpowers' ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/displaySuperpowersPage">Superpowers</a>
                    </li>
                    <li class="${param.title == 'Locations' ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a>
                    </li>
                    <li class="${param.title == 'Organizations' ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a>
                    </li>
                    <li class="${param.title == 'Sightings' ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a>
                    </li>
                </ul>
            </div>
        </nav>