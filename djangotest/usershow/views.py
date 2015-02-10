import json
from django.shortcuts import render
from django.http import HttpResponse, HttpResponseNotFound
from django.contrib.auth.models import User

def index(request):
	response_data = {}
	response_data['users'] = {}
	allusers = User.objects.all()
	for u in allusers:
		response_data['users'][u.id] = u.username
	return HttpResponse(json.dumps(response_data), content_type="application/json")

def specified_user(request, uid):
	response_data = {}
	u = User.objects.filter(id=uid)
	if u:
		u = u[0]
		response_data['id'] = u.id
		response_data['first_name'] = u.first_name
		response_data['last_name'] = u.last_name
		response_data['last_login'] = str(u.last_login)
		response_data['username'] = u.username
		return HttpResponse(json.dumps(response_data), content_type="application/json")
	return HttpResponseNotFound()
