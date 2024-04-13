package moe.shizuku.manager.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.shizuku.manager.databinding.HomeDaemonBinding
import moe.shizuku.manager.databinding.HomeItemContainerBinding
import moe.shizuku.manager.model.ServiceStatus
import rikka.recyclerview.BaseViewHolder
import rikka.recyclerview.BaseViewHolder.Creator

class DaemonViewHolder(private val binding: HomeDaemonBinding, root: View) :
    BaseViewHolder<Pair<ServiceStatus, Int>>(root), View.OnClickListener {

    companion object {
        val CREATOR = Creator<ServiceStatus> { inflater: LayoutInflater, parent: ViewGroup? ->
            val outer = HomeItemContainerBinding.inflate(inflater, parent, false)
            val inner = HomeDaemonBinding.inflate(inflater, outer.root, true)
            DaemonViewHolder(inner, outer.root)
        }
    }

    init {
        root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.setData(Uri.parse("https://lzimul.top"))
            v.context.startActivity(intent)
        } catch (e: Exception) {
            println("当前手机未安装浏览器")
        }
    }
}
