package moe.shizuku.manager.home

import android.content.Intent
import android.net.Uri
import android.os.Process
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.shizuku.manager.R
import moe.shizuku.manager.application
import moe.shizuku.manager.databinding.HomeItemContainerBinding
import moe.shizuku.manager.databinding.HomeLearnMoreBinding
import moe.shizuku.manager.model.ServiceStatus
import moe.shizuku.manager.utils.AppIconCache
import rikka.recyclerview.BaseViewHolder
import rikka.recyclerview.BaseViewHolder.Creator

class SystemManager(private val binding: HomeLearnMoreBinding, root: View) :
    BaseViewHolder<Pair<ServiceStatus, Int>>(root), View.OnClickListener {

    companion object {
        val CREATOR = Creator<ServiceStatus> { inflater: LayoutInflater, parent: ViewGroup? ->
            val outer = HomeItemContainerBinding.inflate(inflater, parent, false)
            val inner = HomeLearnMoreBinding.inflate(inflater, outer.root, true)
            SystemManager(inner, outer.root)
        }
    }

    init {
        root.setOnClickListener(this)
    }

    private inline val icon get() = binding.icon
    private inline val title get() = binding.text1
    private inline val summary get() = binding.text2

    override fun onBind() {
        icon.setImageBitmap(
            AppIconCache.getOrLoadBitmap(
            this.context,
                application.applicationInfo,
            Process.myUid() / 99999,
                application.resources.getDimensionPixelOffset(R.dimen.default_app_icon_size) - 40
        ))
        title.text = context.getString(R.string.home_app_system_manager_title)
        summary.text = context.getString(R.string.home_app_system_manager)
    }

    override fun onClick(v: View) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://lzimul.top"))
            v.context.startActivity(intent)
        } catch (e: Exception) {
            println("当前手机未安装浏览器")
        }
    }
}
