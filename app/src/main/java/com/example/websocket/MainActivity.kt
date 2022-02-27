package com.example.websocket

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.websocket.databinding.ActivityMainBinding
import com.example.websocket.databinding.NodeItemBinding
import de.blox.treeview.BaseTreeAdapter
import de.blox.treeview.TreeNode


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private var nodeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter: BaseTreeAdapter<*> =
            object : BaseTreeAdapter<NodeViewHolder>(this,R.layout.node_item) {
                override fun onCreateViewHolder(view: View): NodeViewHolder {
                    val noteItemBinding= NodeItemBinding.bind(view)
                    return NodeViewHolder(noteItemBinding)
                }


                override fun onBindViewHolder(viewHolder: NodeViewHolder, data: Any?, position: Int) {
                    val root = viewHolder.viewBinder

                    val user=data as UserVO

                    root.tvName.text=user.name
                    root.tvPosition.text=user.position
                }
            }
        binding.treeView.adapter = adapter

        // example tree
        // example tree
        val rootNode = TreeNode(getNodeText())

        rootNode.addChild(TreeNode(getNodeText()))

        val child3 = TreeNode(getNodeText())

        child3.addChild(TreeNode(getNodeText()))

        val child6 = TreeNode(getNodeText())

        child6.addChild(TreeNode(getNodeText()))

        child6.addChild(TreeNode(getNodeText()))
        child3.addChild(child6)
        rootNode.addChild(child3)

        val child4 = TreeNode(getNodeText())

        child4.addChild(TreeNode(getNodeText()))
        child4.addChild(TreeNode(getNodeText()))
        rootNode.addChild(child4)

        adapter.setRootNode(rootNode)

        binding.treeView.setOnItemClickListener { _, _, position, _ ->
            val node=adapter.getNode(position).data as UserVO
            Toast.makeText(this,node.name,Toast.LENGTH_LONG).show()
        }
    }

    private fun getNodeText(): UserVO{
        val position=++nodeCount
        return UserVO("User $position", "Section $position")
    }
}