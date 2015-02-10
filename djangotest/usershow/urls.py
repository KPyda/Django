from django.conf.urls import patterns, url
from usershow import views

urlpatterns = patterns('',
		url(r'^$', views.index, name='index'),
		url(r'^(\d+)/$', views.specified_user, name='user details'),

	)