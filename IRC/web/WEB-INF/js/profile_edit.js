var ProfilerEditor = {
    go:function (el, ev) {
        var current = ProfilerEditor.getsect();
        if (checkEvent(ev) == false) {
            current.removeClass('select');
            el.className = 'select';
        }
        history.pushState({path:this.path}, '', el.href);
        var h = el.href.replace('edit', 'all_edit_profile_entity');
        $.get(h, function (data) {
            $("#profile_right_column").html(data);
        });
        return false;
    },
    getsect:function () {
        return $('#pedit_filters').find(".select");
    },
    saveGeneral:function (btn) {
        $.post('/edit_profile_entity', {action:"general", name:$('#name').val(), surname:$('#surname').val(), genderId:$('#genderId').val(),
            dbDay:$('#db_day').val(), dbMonth:$('#db_month').val(), dbYear:$('#db_year').val(),
            countryId:$('#countryId').val(), regionId:$('#region_row').val(),
            cityId:$('#city_row').val()}, function (response) {
            if (response.complete) {
                alert('Данные сохранены!');
            }
        }, 'json');
        return false;
    },
    saveContacts:function (btn) {
        $.post('/edit_profile_entity', {action:"contacts", cellPhone:$('#cell_phone').val(),
            homePhone:$('#home_phone').val(), skype:$('#skype').val(), ownSite:$('#own_site').val()}, function (response) {
            if (response.complete) {
                alert('Данные сохранены!');
            }
        }, 'json');
        return false;
    },
    saveInterests:function (btn) {
        $.post('/edit_profile_entity', {action:"interests", activities:$('#activities').val(), interests:$('#interests').val(),
            books:$('#books').val(), about:$('#about').val()}, function (response) {
            if (response.complete) {
                alert('Данные сохранены!');
            }
        }, 'json');
        return false;
    }
}

function checkEvent(e) {
    return ((e = (e || window.event)) && (e.type == 'click' || e.type == 'mousedown' || e.type == 'mouseup') && (e.which > 1 || e.button > 1 || e.ctrlKey || e.shiftKey));
}

function changeDate() {
    defineDaysCount();
}

function defineDaysCount() {
    var d = document.getElementById('db_day')
    var m = document.getElementById('db_month');
    var y = document.getElementById('db_year');
    m = m.options[m.selectedIndex];
    y = y.options[y.selectedIndex];

    if (m.value == "na") {
        day_count = 31;
    } else if (m.value == 2) {
        if (y.value == 'na') {
            day_count = 29;
        } else {
            var yr = new Date(y.value, 1, 29).getDate() == 29;
            if (yr) {
                day_count = 29;
            } else {
                day_count = 28;
            }
        }

    } else if (m.value == 4 || m.value == 6 || m.value == 9 || m.value == 11) {
        day_count = 30;
    } else {
        day_count = 31;
    }
    if (d.selectedIndex > day_count + 1) {
        d.selectedIndex = 0;
    }
    if (d.length - 1 > day_count) {
        while (d.length - 1 > day_count) {
            d.remove(d.length - 1);
        }
    } else {
        for (var i = d.length; i < day_count + 1; i++) {
            var opt = document.createElement('option');
            opt.text = i;
            opt.value = i;
            d.appendChild(opt);
        }
    }
}

function fillYears() {
    var el = document.getElementById('db_year');
    for (var i = 1920; i < 2005; i++) {
        var opt = document.createElement('option');
        opt.text = i;
        opt.value = i;
        el.appendChild(opt);
    }
}

function fillBirthDate() {
    fillYears();
    defineDaysCount();
}


function getRegionList(select) {
    var country = select.options[select.selectedIndex].value;
    if (country == 0) {
        var el = document.getElementById('region_row');
        el.setAttribute('style', 'display:none');
        el = document.getElementById('city_row');
        el.setAttribute('style', 'display:none');
    } else {
        $.getJSON('/location/getregions', { countryId:country }, function (regions) {
            $(".region_row").empty();
            var el = document.getElementById('region_row');
            el.setAttribute('style', 'display:block');
            var options = '<option value="0">Не выбрана</option>';
            for (var i = 0; i < regions.length; i++) {
                options += '<option value="' + regions[i].regionId + '">' + regions[i].regionName + '</option>';
            }

            $(".region_row").html(options);
        });
    }
}

function getCityList(select) {
    var region = select.options[select.selectedIndex].value;
    if (region == 0) {
        var el = document.getElementById('city_row');
        el.setAttribute('style', 'display:none');
    } else {
        $.getJSON('/location/getcities', {regionId:region}, function (cities) {
            $(".city_row").empty();
            var el = document.getElementById('city_row');
            el.setAttribute('style', 'display:block');
            var options = '<option value="0">Не выбран</option>';
            for (var i = 0; i < cities.length; i++) {
                options += '<option value="' + cities[i].cityId + '">' + cities[i].cityName + '</option>';
            }
            $(".city_row").html(options);
        });
    }
}



