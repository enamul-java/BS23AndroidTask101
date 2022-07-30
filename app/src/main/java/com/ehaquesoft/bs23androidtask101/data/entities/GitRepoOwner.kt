package com.ehaquesoft.bs23androidtask101.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gitrepoowner")
data class GitRepoOwner(

  /*
  "owner": {
        "login": "flutter",
        "id": 14101776,
        "node_id": "MDEyOk9yZ2FuaXphdGlvbjE0MTAxNzc2",
        "avatar_url": "https://avatars.githubusercontent.com/u/14101776?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/flutter",
        "html_url": "https://github.com/flutter",
        "followers_url": "https://api.github.com/users/flutter/followers",
        "following_url": "https://api.github.com/users/flutter/following{/other_user}",
        "gists_url": "https://api.github.com/users/flutter/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/flutter/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/flutter/subscriptions",
        "organizations_url": "https://api.github.com/users/flutter/orgs",
        "repos_url": "https://api.github.com/users/flutter/repos",
        "events_url": "https://api.github.com/users/flutter/events{/privacy}",
        "received_events_url": "https://api.github.com/users/flutter/received_events",
        "type": "Organization",
        "site_admin": false
      }
   */
  var login:String?,
  var avatar_url:String?,

  @PrimaryKey
  var id:Int,
  var repo_id:Int,

  var url:String?,
  var html_url:String?,
  var followers_url:String?,
  var repos_url:String?,
  var type:String?


)
