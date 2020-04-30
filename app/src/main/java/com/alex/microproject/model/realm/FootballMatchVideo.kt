package com.alex.microproject.model.realm

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class FootballMatchVideo (
    var title: String = "",
    var embed: String = ""
) : RealmModel