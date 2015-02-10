import json #do response
from django.shortcuts import render #standardowo zapisane
from django.http import HttpResponse, HttpResponseNotFound #odpowiedzi HTTP. NotFound to podklasa HttpResponse
from django.contrib.auth.models import User #model uzytkownika

#index wyswietla wszystkich uzytkownikow
def index(request):
	if request.method == 'GET': #jezeli wywolalismy GET (te dwie funkcje tylko pobieraja)
		response_data = {} #tworzymy tablice json response
		response_data['users'] = {} #jako index pobieramy wszystkich uzytkownikow
		allusers = User.objects.all() #pobieramy wszystkich
		if allusers: #jezeli istenieje jakikolwiek uzytkownik
			for u in allusers: #dla kazdego uzytkownika wykonuj
				response_data['users'][u.id] = u.username #zapisz pod jego id jego username
			return HttpResponse(json.dumps(response_data), content_type="application/json") #zwroc json response
	return HttpResponseNotFound() #jezeli nie jest GET albo nie istnieje jakikolwiek uzytkownik - 404

#specified_user wyswietla konkretnego uzytkownika ("dokladniejsze" jego dane, niz w index)
def specified_user(request, uid):
	if request.method == 'GET':
		response_data = {} #tworzymy tablice json response
		u = User.objects.filter(id=uid) #pobieramy uzytkownika o podanym ID
		if u: #jezeli istnieje
			u = u[0] #przedeklarowywujemy z QuerySet do User (zeby dostac sie latwiej do pol)
			response_data['id'] = u.id #ustawiamy "potrzebne" nam dane
			response_data['first_name'] = u.first_name
			response_data['last_name'] = u.last_name
			response_data['last_login'] = str(u.last_login) #datetime, w jakim jest last_login nie jest serializowany do json
			response_data['username'] = u.username
			return HttpResponse(json.dumps(response_data), content_type="application/json") #zwracamy jsona
	return HttpResponseNotFound() #jezeli nie jest GET albo nie istnieje uzytkownik - 404
