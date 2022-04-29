package com.tekydevelop.techicaltestsm.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tekydevelop.domain.model.User
import com.tekydevelop.techicaltestsm.R
import com.tekydevelop.techicaltestsm.databinding.UserItemBinding
import com.tekydevelop.techicaltestsm.utils.GenderType
import com.tekydevelop.techicaltestsm.utils.StatusType
import com.tekydevelop.techicaltestsm.utils.UserDiffUtil
import javax.inject.Inject

class UsersAdapter @Inject constructor(private val listener: (User) -> Unit) : ListAdapter<User, UsersAdapter.ViewHolder>(UserDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
        holder.itemView.setOnLongClickListener {
            listener(currentUser)
            true
        }
    }

    inner class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                user.also {
                    val rootContext = binding.root.context
                    username.text = it.name
                    email.text = it.email

                    if (it.gender == GenderType.FEMALE.name.lowercase()) {
                        genderIcon.load(R.drawable.ic_girl) {
                            crossfade(true)
                        }
                    } else {
                        genderIcon.load(R.drawable.ic_boy) {
                            crossfade(true)
                        }
                    }

                    if (it.status == StatusType.ACTIVE.name.lowercase()) {
                        statusIcon.setColorFilter(getColor(rootContext, R.color.teal_700), android.graphics.PorterDuff.Mode.MULTIPLY)
                    } else {
                        statusIcon.setColorFilter(getColor(rootContext, R.color.teal_200), android.graphics.PorterDuff.Mode.MULTIPLY)
                    }
                }
            }
        }
    }

}
